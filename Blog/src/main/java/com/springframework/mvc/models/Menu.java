package com.springframework.mvc.models;

public class Menu {
	
	private int Id;
	
	private String MenuName;
	
	private int State;
	
	private int BlogId;
	
	private Blog Blog;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public int getBlogId() {
		return BlogId;
	}

	public void setBlogId(int blogId) {
		BlogId = blogId;
	}

	public Blog getBlog() {
		return Blog;
	}

	public void setBlog(Blog blog) {
		Blog = blog;
	}

}
