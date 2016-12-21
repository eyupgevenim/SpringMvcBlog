package com.springframework.mvc.dao;

import java.util.List;
import java.util.Map;

import com.springframework.mvc.models.Blog;
import com.springframework.mvc.models.Menu;

public interface BlogDao {
	
	boolean saveBlog(Blog blog);
	
	Blog findBlogByBlogUrl(String blogUrl);
	
	boolean isThereSameBlogUrl(String blogUrl);
	
	List<Blog> getUserBlogs(String email);
	
	Map<String, Map<String, String>> getAllMenus();
	
	Map<String, Map<String, String>> getUndefineAllMenus(List<Menu> blogMenus);
	
	List<Menu> getBlogsMenus(String blogUrl);
	
	boolean addBlogMenus(final List<Menu> menus);
	
	boolean deleteMenu(int menuId,int blogId);
	
	Menu getMenuByMenuName(String menuName , String blogUrl);
	
	Menu getMenuByMenuId(int menuId , int blogId);
	
}
