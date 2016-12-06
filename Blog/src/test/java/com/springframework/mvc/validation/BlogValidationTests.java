package com.springframework.mvc.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.models.Blog;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlogValidationTests extends AbstractContextControllerTests {
	
	@Autowired
	BlogValidation blogValidation;
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void testValidationSuccess(){
		Blog blog = new Blog(){
			{
				setBlogName("testBlog");
				setBlogUrl("Test Blog");
			}
		};
		
		Errors errors = new BeanPropertyBindingResult(blog, "blog");
		blogValidation.validate(blog, errors);
		assertFalse(errors.hasErrors()); 
		
	}
	
	@Test
	public void testValidationError(){
		Blog blog = new Blog(){
			{
				setBlogName("te");
				setBlogUrl("Test Blog");
			}
		};
		
		Errors errors = new BeanPropertyBindingResult(blog, "blog");
		blogValidation.validate(blog, errors);
		assertTrue(errors.hasErrors());
		assertTrue( errors.getErrorCount() == 1 );
		assertEquals("Blog ad\u0131 en az ьз karekter olmal\u0131.", 
				getConfiguredMessage(errors.getFieldError("BlogName")));
		
	}
	
	private String getConfiguredMessage(FieldError fieldError) {
	    return messageSource.getMessage(fieldError.getCode(), 
	                                    fieldError.getArguments(), 
	                                    Locale.US);
	}
	
}
