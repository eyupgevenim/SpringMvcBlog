package com.springframework.mvc.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.springframework.mvc.dao.BlogDao;
import com.springframework.mvc.models.Blog;
import com.springframework.mvc.models.Menu;

@Component
public class BlogDaoImpl implements BlogDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	}

	@Override
	public boolean saveBlog(final Blog blog) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		/*
		String sql = "INSERT INTO blogs(UserId, BlogUrl, BlogName, CategoryId) VALUES(?,?,?,?)";
		
		Object[] args =new Object[]{blog.getUser().getId(),blog.getBlogUrl(),blog.getBlogName(),blog.getCategoryId()};
		
		int result = jdbcTemplate.update(sql, args);
		
		if(result != 0){
			return true;
		} else {
			return false;
		}
		
		*/
		
		final String sql = "INSERT INTO blogs(UserId, BlogUrl, BlogName, CategoryId) VALUES(?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int result = jdbcTemplate.update( new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	            PreparedStatement pst = con.prepareStatement(sql, new String[] {"Id"});
	            pst.setInt(1, blog.getUser().getId());
	            pst.setString(2, blog.getBlogUrl());
	            pst.setString(3, blog.getBlogName());
	            pst.setInt(4, blog.getCategoryId());
	            return pst;
	        }
	    }, keyHolder);
		
		final Long lastId = (Long)keyHolder.getKey();
		
		if(result != 0){
			
			List<Menu> menus = new ArrayList<Menu>();
			Menu m = new Menu(){
				{
					setId(1);
					setBlogId(lastId.intValue());
				}
			};
			menus.add(m);
			addBlogMenus(menus);
			
			return true;
		} else {
			return false;
		}
	}
	
	public Blog findBlogByBlogUrl(String blogUrl){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM blogs WHERE BlogUrl = ?";
		Blog blog = jdbcTemplate.queryForObject(sql, new Object[]{blogUrl}, new RowMapper<Blog>(){
			@Override
			public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
				Blog blog= new Blog();
				blog.setId(rs.getInt("Id"));
				blog.setBlogName(rs.getString("BlogName"));
				blog.setBlogUrl(rs.getString("BlogUrl"));
				blog.setCategoryId(rs.getInt("CategoryId"));
				blog.setTheme(rs.getString("Theme"));
				return blog;
			}});
		
		return blog;
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
					
					return blog;
				}
				
			});
		
		  return listBlog;
	}
	
	public boolean addBlogMenus(final List<Menu> menus){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO blogsmenus (MenuId, BlogId) VALUES (?, ?)";
		
		try {
			
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Menu menu = menus.get(i);
					ps.setInt(1, menu.getId());
					ps.setInt(2, menu.getBlogId());
				}

				@Override
				public int getBatchSize() {
					return menus.size();
				}
			  });
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public Map<String, Map<String, String>> getAllMenus(){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
        String sql = "SELECT * FROM menus";
        
        Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
        Map<String,String> menu = new LinkedHashMap<String,String>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
        	menu.put(String.valueOf(row.get("Id")),(String)row.get("MenuName"));
        }
        
		data.put("menu",menu);
		return data;
	}
	
	public Map<String, Map<String, String>> getUndefineAllMenus(List<Menu> blogMenus){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String in="";
		Object[] arg = new Object[blogMenus.size()];
		
		for (int i = 0; i < blogMenus.size(); i++) {
			
			in+="?";
			if((blogMenus.size() - 1 ) != i ){
				in += ",";
			}
			
			arg[i] = blogMenus.get(i).getId();
		}
		
        String sql = "SELECT * FROM menus WHERE Id NOT IN("+in+")";
        
        Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
        Map<String,String> menu = new LinkedHashMap<String,String>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,arg);
        for (Map<String, Object> row : rows) {
        	menu.put(String.valueOf(row.get("Id")),(String)row.get("MenuName"));
        }
        
		data.put("menu",menu);
		return data;
	}
	
	public List<Menu> getBlogsMenus(String blogUrl){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM menus m "
				+ "INNER JOIN blogsmenus bm ON m.Id=bm.MenuId "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE b.BlogUrl=?";// AND m.Id != 1";
		
		List<Menu> listMenu= jdbcTemplate.query(sql, new Object[]{blogUrl}, new RowMapper<Menu>() {

				@Override
				public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
					Menu menu = new Menu();
		
					menu.setId(rs.getInt("m.Id"));
					menu.setMenuName(rs.getString("m.MenuName"));
					menu.setState(rs.getInt("m.State"));
					
					return menu;
				}
				
			});
		
		  return listMenu;
	}
	
	public boolean deleteMenu(int menuId,int blogId){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "DELETE FROM blogsmenus WHERE MenuId=? AND BlogId=?";
		
		Object[] args =new Object[]{menuId,blogId};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public Menu getMenuByMenuName(String menuName , String blogUrl){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM menus m "
				+ "INNER JOIN blogsmenus bm ON m.Id=bm.MenuId "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE m.MenuName=? AND b.BlogUrl=? LIMIT 1";
		
		Menu menu = jdbcTemplate.queryForObject(sql, new Object[]{menuName,blogUrl}, new RowMapper<Menu>(){
			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu menu= new Menu();
				menu.setId(rs.getInt("bm.Id"));
				menu.setBlogId(rs.getInt("b.Id"));
				return menu;
			}});
		
		return menu;
	}
	
	public Menu getMenuByMenuId(int menuId , int blogId){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM menus m "
				+ "INNER JOIN blogsmenus bm ON m.Id=bm.MenuId "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE m.Id=? AND b.Id=? LIMIT 1";
		
		Menu menu = jdbcTemplate.queryForObject(sql, new Object[]{menuId,blogId}, new RowMapper<Menu>(){
			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu menu = new Menu();
				Blog blog = new Blog();
				blog.setBlogName(rs.getString("b.BlogName"));
				
				menu.setId(rs.getInt("bm.Id"));
				menu.setBlog(blog);
				menu.setMenuName(rs.getString("m.MenuName"));
				return menu;
			}});
		
		return menu;
	}

}
