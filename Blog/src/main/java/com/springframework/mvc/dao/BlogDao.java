package com.springframework.mvc.dao;

import java.util.List;

import com.springframework.mvc.models.Blog;

public interface BlogDao {
	
	boolean saveBlog(Blog blog);
	
	boolean isThereSameBlogUrl(String blogUrl);
	
	List<Blog> getUserBlogs(String email);

}
