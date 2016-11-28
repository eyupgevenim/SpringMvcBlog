package com.springframework.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import com.springframework.mvc.models.User;

public interface UserDao {
	
	boolean saveUser(User user);

	List<User> findAllUsers();
	
	User findByEmail(String email);
	
	boolean isThereSameEmail(String email);
	
	boolean isThereSameUserName(String username);
	
	boolean isValidUser(String email, String password) throws SQLException;
	
	boolean isConfirmOldPassword(String email, String password);
	
	boolean updatePassword(String email, String password);
	
	boolean updateInformation(User user);
	
	boolean isThereSameEmailOutsideOfOwn(User user);
}
