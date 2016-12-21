package com.springframework.mvc.models;

import java.sql.Date;

public class Post {
	
	private int Id;
	
	private String PostTite;
	
	private String PostContent;
	
	private String RequestMapping;
	
	private int BlogMenuId;
	
	private Menu Menu;
	
	private Date DateTime;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPostTite() {
		return PostTite;
	}

	public void setPostTite(String postTite) {
		PostTite = postTite;
	}

	public String getPostContent() {
		return PostContent;
	}

	public void setPostContent(String postContent) {
		PostContent = postContent;
	}

	public String getRequestMapping() {
		return RequestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		RequestMapping = requestMapping;
	}

	public int getBlogMenuId() {
		return BlogMenuId;
	}

	public void setBlogMenuId(int blogMenuId) {
		BlogMenuId = blogMenuId;
	}

	public Menu getMenu() {
		return Menu;
	}

	public void setMenu(Menu menu) {
		Menu = menu;
	}
	
	public Date getDateTime() {
		return DateTime;
	}
	
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

}
