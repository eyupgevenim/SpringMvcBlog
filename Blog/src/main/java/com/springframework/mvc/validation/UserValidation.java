package com.springframework.mvc.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

import com.springframework.mvc.dao.UserDao;
import com.springframework.mvc.models.User;


@Component
public class UserValidation implements Validator {
	
	@Autowired
	UserDao userDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserName", "user.UserName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FirstName", "user.UserName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "LastName", "user.UserName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Email", "user.Email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Password", "user.Password.empty");
		
		if(user.getUserName().length() < 3){
			errors.rejectValue("UserName","user.UserName.size");
		}
		
		if(user.getFirstName().length() < 3){
			errors.rejectValue("FirstName","user.FirstName.size");
		}
		
		if(user.getLastName().length() < 3){
			errors.rejectValue("LastName","user.LastName.size");
		}
		
		
		if(userDAO.isThereSameUserName(user.getUserName())){
			errors.rejectValue("UserName","user.UserName.thereIsSame",
					new Object[]{user.getFirstName(),user.getLastName()},"");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())){
			errors.rejectValue("ConfirmPassword","user.ConfirmPassword.missMatch");
		}
		
		if(userDAO.isThereSameEmail(user.getEmail())){
			errors.rejectValue("Email","user.Email.thereIsSame");
		}
		
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(!pattern.matcher(user.getEmail()).matches()){
			errors.rejectValue("Email","user.Email.format");
		}
		
	}

}
