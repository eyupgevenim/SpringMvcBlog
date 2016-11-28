package com.springframework.mvc.dao.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import com.springframework.mvc.dao.BlogDao;
import com.springframework.mvc.models.Blog;

@Component
public class BlogDaoImpl implements BlogDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	}

	@Override
	public boolean saveBlog(Blog blog) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO blogs(UserId, BlogUrl, BlogName, CategoryId) VALUES(?,?,?,?)";
		
		Object[] args =new Object[]{blog.getUser().getId(),blog.getBlogUrl(),blog.getBlogName(),blog.getCategoryId()};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isThereSameBlogUrl(String blogUrl){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT COUNT(*) FROM blogs WHERE BlogUrl=?";
		
		Object[] args =new Object[]{blogUrl};
		int result = jdbcTemplate.queryForObject(sql, args, Integer.class);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public List<Blog> getUserBlogs(String email){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM users u INNER JOIN blogs b ON u.Id=b.UserId WHERE Email=?";
		
		List<Blog> listBlog= jdbcTemplate.query(sql, new Object[]{email}, new RowMapper<Blog>() {

				@Override
				public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
					Blog blog = new Blog();
		
					blog.setId(rs.getInt("b.Id"));
					blog.setBlogName(rs.getString("b.BlogName"));
					blog.setBlogUrl(rs.getString("b.BlogUrl"));
					
					/*
					User user = new User();
					user.setId(rs.getInt("u.Id"));
					user.setUserName(rs.getString("u.UserName"));
					user.setFirstName(rs.getString("u.FirstName"));
					user.setLastName(rs.getString("u.LastName"));
					user.setEmail(rs.getString("u.Email"));
					blog.setUser(user);
					*/
					
					return blog;
				}
				
			});
		
		  return listBlog;
		
		/*
		List<Blog> blogs = new ArrayList<Blog>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[]{email});
        for (Map<String, Object> row : rows) {
            Blog blog = new Blog();
            //blog.setId(Integer.parseInt(String.valueOf(row.get("b.Id"))));
            blog.setBlogName((String)row.get("b.BlogName"));
            blog.setBlogUrl((String)row.get("b.BlogUrl"));
            
            
            User user = new User();
            user.setId(Integer.parseInt(String.valueOf(row.get("u.Id"))));
            user.setUserName((String)row.get("u.UserName"));
            user.setFirstName((String)row.get("u.FirstName"));
			user.setLastName((String)row.get("u.LastName"));
			user.setEmail((String)row.get("u.Email"));
            
			blog.setUser(user);
            
            
            
            blogs.add(blog);
        }

		return blogs;
		*/
		
	}
	

}
