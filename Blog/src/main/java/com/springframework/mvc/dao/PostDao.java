package com.springframework.mvc.dao;

import java.util.List;

import com.springframework.mvc.models.Comment;
import com.springframework.mvc.models.Post;

public interface PostDao {
	
	boolean savePost(Post post);
	
	boolean updatePost(Post post);
	
	Post getPost(String requestMapping);
	
	boolean isThereSameRequestMapping(String requestMapping);
	
	List<Post> getBlogPosts(String blogUrl);
	
	List<Post> getBlogPosts(String menuName, String blogUrl);
	
	List<Post> getBlogPosts(int menuId, int blogId);
	
	Post getBlogPost(String menuName, String blogUrl);
	
	boolean saveVisitorAndComment(Comment comment);
	
	boolean saveVisitor(Comment comment);
	
	List<Comment> getComents(String requestMapping);
	
	List<Comment> getBlogComents(String blogUrl);

}
