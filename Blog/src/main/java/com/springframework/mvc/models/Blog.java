package com.springframework.mvc.models;

public class Blog {
	
	private int Id;
	
	private User User;
	
	private String BlogName;
	
	private String BlogUrl;
	
	private String Theme;
	
	private int CategoryId;
	
	private Category Category;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public String getBlogName() {
		return BlogName;
	}

	public void setBlogName(String blogName) {
		BlogName = blogName;
	}

	public String getBlogUrl() {
		return BlogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		BlogUrl = blogUrl;
	}

	public String getTheme() {
		return Theme;
	}

	public void setTheme(String theme) {
		Theme = theme;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

}
