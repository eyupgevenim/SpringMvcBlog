package com.springframework.mvc.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springframework.mvc.models.Blog;

@Component
public class BlogValidation implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Blog.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Blog blog = (Blog)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "BlogUrl", "blog.BlogUrl.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "BlogName", "blog.BlogName.empty");

		if(blog.getBlogUrl().length() < 3){
			errors.rejectValue("BlogUrl","blog.BlogUrl.size");
		}
		
		if(blog.getBlogName().length() < 3){
			errors.rejectValue("BlogName","blog.BlogName.size");
		}
		
		
	}

}
