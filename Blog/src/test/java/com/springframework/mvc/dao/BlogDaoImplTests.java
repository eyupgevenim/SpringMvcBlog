package com.springframework.mvc.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.models.Blog;
import com.springframework.mvc.models.Category;
import com.springframework.mvc.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlogDaoImplTests extends AbstractContextControllerTests {
	
	@Autowired
    private BlogDao blogDAO;
	
	@Test
	public void testSaveBlog(){
		
		final User user = new User(){
			{
				setId(2);
			}
		};
		
		final Category category = new Category(){
			{
				setId(3);
			}
		};
		
		Blog blog = new Blog(){
			{
				setUser(user);
				setCategory(category);
				setBlogUrl("testTest");
				setBlogName("Test Test");
			}
		};
		
		assertTrue(blogDAO.saveBlog(blog));
	}
	
	@Test
	public void testIsThereSameBlogUrl(){
		
		assertTrue(blogDAO.isThereSameBlogUrl("eyupgevenim"));
		assertFalse(blogDAO.isThereSameBlogUrl("EyüpGevenim"));
	}
	
	@Test
	public void testGetUserBlogs(){
		
		List<Blog> blogs = new ArrayList<Blog>();
		blogs = blogDAO.getUserBlogs("e@e.com");
		assertNotNull(blogs);
		assertTrue(blogs.size() >= 2);
	}
	
}
