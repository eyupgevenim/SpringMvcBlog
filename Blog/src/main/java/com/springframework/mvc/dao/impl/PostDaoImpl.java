package com.springframework.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.springframework.mvc.dao.PostDao;
import com.springframework.mvc.models.Comment;
import com.springframework.mvc.models.Post;

@Component
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	}

	@Override
	public boolean savePost(Post post) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO posts(PostTitle, PostContent, RequestMapping, BlogMenuId) VALUES(?,?,?,?)";
		
		Object[] args =new Object[]{post.getPostTite(),post.getPostContent(),post.getRequestMapping(),post.getBlogMenuId()};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updatePost(Post post) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "UPDATE posts SET PostContent=? WHERE Id=?";
		
		Object[] args =new Object[]{post.getPostContent(),post.getId()};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Post getPost(String requestMapping) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM posts WHERE RequestMapping = ? LIMIT 1";
		
		Post post = jdbcTemplate.queryForObject(sql, new Object[]{requestMapping}, new RowMapper<Post>(){
			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post= new Post();
				post.setId(rs.getInt("Id"));
				post.setPostTite(rs.getString("PostTitle"));
				post.setPostContent(rs.getString("PostContent"));
				post.setRequestMapping(rs.getString("RequestMapping"));
				post.setBlogMenuId(rs.getInt("BlogMenuId"));
				post.setDateTime(rs.getDate("DateTime"));
				return post;
			}});
		
		return post;
	}
	
	public boolean isThereSameRequestMapping(String requestMapping){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT COUNT(*) FROM posts WHERE RequestMapping = ?";
		Object[] args =new Object[]{requestMapping};
		Number number = jdbcTemplate.queryForObject(sql, args,Integer.class);
		int count = number != null ? number.intValue() : 0;
		
		if(count != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public List<Post> getBlogPosts(String blogUrl){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM posts p "
				+ "INNER JOIN blogsmenus bm ON p.BlogMenuId=bm.Id "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE b.BlogUrl=?";
		
		List<Post> listPost= jdbcTemplate.query(sql, new Object[]{blogUrl}, new RowMapper<Post>() {

				@Override
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new Post();
					post.setId(rs.getInt("p.Id"));
					post.setPostTite(rs.getString("p.PostTitle"));
					post.setPostContent(rs.getString("p.PostContent"));
					post.setRequestMapping(rs.getString("p.RequestMapping"));
					post.setDateTime(rs.getDate("p.DateTime"));
					
					return post;
				}
				
			});
		
		  return listPost;
	}
	
	public List<Post> getBlogPosts(String menuName, String blogUrl){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM posts p "
				+ "INNER JOIN blogsmenus bm ON p.BlogMenuId=bm.Id "
				+ "INNER JOIN menus m ON m.Id=bm.MenuId "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE m.MenuName=? AND b.BlogUrl=?";
		
		List<Post> listPost= jdbcTemplate.query(sql, new Object[]{menuName,blogUrl}, new RowMapper<Post>() {

				@Override
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new Post();
					post.setId(rs.getInt("p.Id"));
					post.setPostTite(rs.getString("p.PostTitle"));
					post.setPostContent(rs.getString("p.PostContent"));
					post.setRequestMapping(rs.getString("p.RequestMapping"));
					post.setDateTime(rs.getDate("p.DateTime"));
					
					return post;
				}
				
			});
		
		  return listPost;
	}
	
	public List<Post> getBlogPosts(int menuId, int BlogId){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM posts p "
				+ "INNER JOIN blogsmenus bm ON p.BlogMenuId=bm.Id "
				+ "INNER JOIN menus m ON m.Id=bm.MenuId "
				+ "WHERE m.Id=? AND bm.BlogId=?";
		
		List<Post> listPost= jdbcTemplate.query(sql, new Object[]{menuId,BlogId}, new RowMapper<Post>() {

				@Override
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new Post();
					post.setId(rs.getInt("p.Id"));
					post.setPostTite(rs.getString("p.PostTitle"));
					post.setPostContent(rs.getString("p.PostContent"));
					post.setRequestMapping(rs.getString("p.RequestMapping"));
					post.setDateTime(rs.getDate("p.DateTime"));
					
					return post;
				}
				
			});
		
		  return listPost;
	}
	
	public Post getBlogPost(String menuName, String blogUrl){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM posts p "
				+ "INNER JOIN blogsmenus bm ON p.BlogMenuId=bm.Id "
				+ "INNER JOIN menus m ON m.Id=bm.MenuId "
				+ "INNER JOIN blogs b ON b.Id=bm.BlogId "
				+ "WHERE m.MenuName=? AND b.BlogUrl=? LIMIT 1";
		try {
		
			Post post = jdbcTemplate.queryForObject(sql, new Object[]{menuName,blogUrl}, new RowMapper<Post>() {
	
					@Override
					public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
						Post post = new Post();
						post.setId(rs.getInt("p.Id"));
						post.setPostTite(rs.getString("p.PostTitle"));
						post.setPostContent(rs.getString("p.PostContent"));
						post.setRequestMapping(rs.getString("p.RequestMapping"));
						post.setDateTime(rs.getDate("p.DateTime"));
						
						return post;
					}
					
				});
			
			  return post;
		  
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public boolean saveVisitorAndComment(final Comment comment){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		final String sql = "INSERT INTO visitors(VisitorName,Email) VALUES(?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int result = jdbcTemplate.update( new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	            PreparedStatement pst = con.prepareStatement(sql, new String[] {"Id"});
	            pst.setString(1, comment.getName());
	            pst.setString(2, comment.getEmail());
	            return pst;
	        }
	    }, keyHolder);
		
		final Long lastId = (Long)keyHolder.getKey();
		
		if(result != 0){
			comment.setCommentatorId(lastId.intValue());
			return saveVisitor(comment);
		} else {
			return false;
		}
	}
	
	public boolean saveVisitor(Comment comment){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO comments(PostId, Comment, CommentatorId) VALUES(?,?,?)";
		
		Object[] args =new Object[]{comment.getPostId(),comment.getComment(),comment.getCommentatorId()};
		int result = jdbcTemplate.update(sql, args);
		if(result != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public List<Comment> getComents(String requestMapping){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM comments c "
				+ "INNER JOIN visitors v ON c.CommentatorId=v.Id "
				+ "INNER JOIN posts p ON c.PostId=p.Id "
				+ "WHERE p.RequestMapping=?";
		
		List<Comment> listComment= jdbcTemplate.query(sql, new Object[]{requestMapping}, new RowMapper<Comment>() {

				@Override
				public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
					Comment comment = new Comment();
					comment.setId(rs.getInt("c.Id"));
					comment.setPostId(rs.getInt("p.Id"));
					comment.setComment(rs.getString("c.Comment"));
					comment.setName(rs.getString("v.VisitorName"));
					comment.setEmail(rs.getString("v.Email"));
					comment.setDateTime(rs.getDate("c.DateTime"));
					comment.setState(rs.getInt("c.State"));
					
					return comment;
				}
				
			});
		
		  return listComment;
	}
	
	public List<Comment> getBlogComents(String blogUrl){

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM comments c "
				+ "INNER JOIN visitors v ON c.CommentatorId=v.Id "
				+ "INNER JOIN posts p ON c.PostId=p.Id "
				+ "INNER JOIN blogsmenus bm ON p.BlogMenuId=bm.Id "
				+ "INNER JOIN blogs b ON bm.BlogId=b.Id "
				+ "WHERE b.BlogUrl=?";
		
		List<Comment> listComment= jdbcTemplate.query(sql, new Object[]{blogUrl}, new RowMapper<Comment>() {

				@Override
				public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
					Comment comment = new Comment();
					comment.setId(rs.getInt("c.Id"));
					comment.setPostId(rs.getInt("p.Id"));
					comment.setComment(rs.getString("c.Comment"));
					comment.setName(rs.getString("v.VisitorName"));
					comment.setEmail(rs.getString("v.Email"));
					comment.setDateTime(rs.getDate("c.DateTime"));
					comment.setState(rs.getInt("c.State"));
					comment.setPostTitle(rs.getString("p.PostTitle"));
					
					return comment;
				}
				
			});
		
		  return listComment;
	}
	

}
