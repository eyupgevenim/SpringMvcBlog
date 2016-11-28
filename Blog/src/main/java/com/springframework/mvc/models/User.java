package com.springframework.mvc.models;

import org.joda.time.DateTime;

public class User {

	private int Id;
	
	private String UserName;

	private String FirstName;

	private String LastName;

	private String Email;

	private String Password;
	
	private String ConfirmPassword;
	
	private DateTime DateTime;

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	
	public DateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(DateTime dateTime) {
		DateTime = dateTime;
	}
}
