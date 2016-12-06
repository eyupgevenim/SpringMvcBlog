package com.springframework.mvc.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.mvc.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailValidationTests extends AbstractContextControllerTests {
	
	EmailValidation ev;
	
	@Before
	public void setup(){
		ev = new EmailValidation();
	}
	
	@Test
	public void testValidationSuccess(){
		assertTrue(ev.isValid("test@test.com"));
		assertTrue(ev.isValid("test@test.test"));
		assertTrue(ev.isValid("test123@test.com"));
		assertTrue(ev.isValid("test123@test123.com"));
		assertTrue(ev.isValid("123@test123.com"));
	}
	
	@Test
	public void testValidationError(){
		assertFalse(ev.isValid("test@test"));
		assertFalse(ev.isValid("test.com"));
		assertFalse(ev.isValid("test@test.c"));
		assertFalse(ev.isValid("test@test.123"));
	}
	
	
}
