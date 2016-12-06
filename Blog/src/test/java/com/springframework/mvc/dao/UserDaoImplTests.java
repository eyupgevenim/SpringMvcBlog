package com.springframework.mvc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoImplTests extends AbstractContextControllerTests {
	
	@Autowired
    private UserDao userDAO;
	
	@Test
	public void testSaveUser(){
		
		User user = new User(){
			{
				setUserName("test");
				setFirstName("TestFirst");
				setLastName("TestLast");
				setEmail("t@t.com");
				setPassword("123");
				setConfirmPassword("123");
			}
		};
		assertTrue(userDAO.saveUser(user));
	}
	
	@Test
	public void testFindByEmail(){
		
		User user = userDAO.findByEmail("e@e.com");
		assertNotNull(user);
		assertEquals(user.getUserName(), "demoUser");
	}
	
	@Test
	public void testIsThereSameEmail(){
		
		assertTrue(userDAO.isThereSameEmail("e@e.com"));
	}
	
	@Test
	public void testIsThereSameUserName(){
		
		assertTrue(userDAO.isThereSameUserName("eyupgevenim"));
	}
	
	@Test
	public void testFindAllUsers(){
		
		List<User> users = new ArrayList<User>();
		users =  userDAO.findAllUsers();
		assertTrue(users.size() > 2);
	}
	
	@Test
	public void testIsConfirmOldPassword(){
		
		assertTrue(userDAO.isConfirmOldPassword("e@e.com", "123"));
	}
	
	@Test
	public void testUpdatePassword(){
		
		assertTrue(userDAO.updatePassword("123","e@e.com"));
	}
	
	@Test
	public void testUpdateInformation(){
		
		User user = new User(){
			{
				setId(2);
				setFirstName("updateTestFirst");
				setLastName("updateTestLast");
				setEmail("update@test.com");
			}
		};
		
		userDAO.updateInformation(user);
	}
	
}
