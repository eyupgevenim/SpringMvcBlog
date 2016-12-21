package com.springframework.mvc.models;

import java.sql.Date;

public class Comment {
	
	private int Id;
	
	private int PostId;
	
	private String Comment;
	
	private int State;
	
	private Date DateTime;
	
	private int CommentatorId;
	
	private String Name;
	
	private String Email;
	
	private String PostTitle;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

	public int getCommentatorId() {
		return CommentatorId;
	}

	public void setCommentatorId(int commentatorId) {
		CommentatorId = commentatorId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPostTitle() {
		return PostTitle;
	}

	public void setPostTitle(String postTitle) {
		PostTitle = postTitle;
	}

}
