package com.springframework.mvc.validation;

import static org.junit.Assert.*;

import java.util.Locale;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.models.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserValidationTests extends AbstractContextControllerTests {

	@Autowired
	UserValidation userValidation;
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void testValidateErrors(){
		
		User user = new User(){
			{
				setUserName("ey");
				setFirstName("Eyup");
				setLastName("Gevenim");
				setEmail("e@e.com");
				setPassword("123");
				setConfirmPassword("12");
			}
		};
		
		Errors errors = new BeanPropertyBindingResult(user, "user");
		userValidation.validate(user, errors);
		assertTrue(errors.hasErrors());
		assertTrue( errors.getErrorCount() == 3 );
		assertEquals("Kullan\u0131c\u0131 ad\u0131 en az ьз karekter olmal\u0131.", 
				getConfiguredMessage(errors.getFieldError("UserName")));
		
	}
	
	
	@Test
	public void testValidateSuccess() throws Exception {
		
		User user = new User(){
			{
				setUserName("testSuccess");
				setFirstName("Test");
				setLastName("Test");
				setEmail("testValidationSuccess@test.com");
				setPassword("123");
				setConfirmPassword("123");
			}
		};
		
		Errors errors = new BeanPropertyBindingResult(user, "user");
		userValidation.validate(user, errors);
		assertFalse(errors.hasErrors()); 
	}
	
	private String getConfiguredMessage(FieldError fieldError) {
	    return messageSource.getMessage(fieldError.getCode(), 
	                                    fieldError.getArguments(), 
	                                    Locale.US);
	}
}
