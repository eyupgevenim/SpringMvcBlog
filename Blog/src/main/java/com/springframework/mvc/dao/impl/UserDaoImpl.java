package com.springframework.mvc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springframework.mvc.dao.UserDao;
import com.springframework.mvc.models.User;

@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		        this.dataSource = dataSource;
	}
	
	
	public boolean saveUser(User user) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO users(UserName, FirstName, LastName, Email, Password) VALUES(?,?,?,?,?)";
		//this will change setMd5Encoder()
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String hashedPassword = passwordEncoder.encodePassword(user.getPassword(), null);
		Object[] args =new Object[]{user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), hashedPassword};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	

	public List<User> findAllUsers(){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
        String sql = "SELECT * FROM users WHERE Enabled=1";
        List<User> users = new ArrayList<User>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            User user = new User();
            user.setId(Integer.parseInt(String.valueOf(row.get("Id"))));
            user.setUserName((String)row.get("UserName"));
            user.setFirstName((String)row.get("FirstName"));
			user.setLastName((String)row.get("LastName"));
			user.setEmail((String)row.get("Email"));
			user.setPassword((String)row.get("Password"));
            
            users.add(user);
        }

		return users;
	}
	
	
	public User findByEmail(String email){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM users WHERE Enabled=1 AND Email = ?";
		User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user= new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				return user;
			}});
		
		return user;
	}
	
	
	public boolean isThereSameEmail(String email){
		String query = "SELECT COUNT(1) FROM users WHERE Email = ?";
		try {
			
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
	        pstmt.setString(1, email);
	        ResultSet resultSet = pstmt.executeQuery();
	        if(resultSet.next())
	            return (resultSet.getInt(1) > 0);
	        else
	           return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean isThereSameUserName(String username){
		String query = "SELECT COUNT(1) FROM users WHERE UserName = ?";
		try {
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
	        pstmt.setString(1, username);
	        ResultSet resultSet = pstmt.executeQuery();
	        if(resultSet.next())
	            return (resultSet.getInt(1) > 0);
	        else
	           return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean isValidUser(String email, String password) throws SQLException {
        String query = "SELECT COUNT(1) FROM users WHERE Email = ? AND Password = ?";
        PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, setMd5Encoder(password));
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next())
            return (resultSet.getInt(1) > 0);
        else
           return false;
    }
	
	public boolean isConfirmOldPassword(String email, String password){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT COUNT(*) FROM users WHERE Email = ? AND Password = ?";
		Object[] args =new Object[]{email, setMd5Encoder(password)};
		//int result = jdbcTemplate.queryForInt(sql, args,Integer.class);
		Number number = jdbcTemplate.queryForObject(sql, args,Integer.class);
		int count = number != null ? number.intValue() : 0;
		
		if(count != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updatePassword(String password, String email){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "UPDATE users SET Password = ? WHERE Email = ?";
		Object[] args =new Object[]{setMd5Encoder(password), email};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateInformation(User user){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "UPDATE users SET FirstName = ?, LastName = ?, Email = ? WHERE Id = ?";
		Object[] args =new Object[]{user.getFirstName(), user.getLastName(),user.getEmail(),user.getId()};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isThereSameEmailOutsideOfOwn(User user){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT COUNT(*) FROM users WHERE Email = ? AND Id != ?";
		Object[] args =new Object[]{user.getEmail(),user.getId()};
		Number number = jdbcTemplate.queryForObject(sql, args,Integer.class);
		int count = number != null ? number.intValue() : 0;
		
		if(count != 0){
			return true;
		} else {
			return false;
		}
	}
	
	private String setMd5Encoder(String password){
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		return hashedPassword;
	}

	
}
