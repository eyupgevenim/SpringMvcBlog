package com.springframework.mvc.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.dao.CategoryDao;


@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/spring-security.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests extends AbstractContextControllerTests {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	CategoryDao categoryDAO;

	@Before
	public void setup() throws Exception {
		
		/*
		this.mockMvc = MockMvcBuilders
							.webAppContextSetup(context)
							.apply(springSecurity())
							.build();
		*/
		
		this.mockMvc = MockMvcBuilders
							.webAppContextSetup(context)
							//.defaultRequest(get("/user").with(user("e@e.com").roles("USER")))
							.apply(springSecurity())
							.build();
	}
	
	@Test
	public void testUserView() throws Exception{
		this.mockMvc.perform(get("/user").with(user("e@e.com").roles("USER")))
		.andExpect(status().isOk())
	.andExpect(view().name(containsString("users/manages/user")));
		
	}
	
	@Test
	public void testSettingView() throws Exception{
		this.mockMvc.perform(get("/user/setting").with(user("e@e.com").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(model().attribute("userName", "demoUser"))
		.andExpect(model().attribute("firstName", "Eyüp"))
		.andExpect(model().attribute("lastName", "Gevenim"))
		.andExpect(model().attribute("email", "e@e.com"))
		.andExpect(view().name(containsString("users/manages/user-settings")));
		
	}
	
	@Test
	public void testNewBlogView() throws Exception{
		this.mockMvc.perform(get("/user/newBlog").with(user("e@e.com").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(model().attribute("category", categoryDAO.findAllCategory()))
		.andExpect(view().name(containsString("users/manages/user-new-blog")));
	}
	
	@Test
	public void testAddNewBlog() throws Exception{
		this.mockMvc.perform(post("/user/newBlog")
				.header("X-Requested-With", "XMLHttpRequest")
				.param("BlogName", "Test Blog Test")
				.param("BlogUrl", "testBloTest")
				.param("CategoryId", "1")
				.with(csrf())
				.with(user("e@e.com").roles("USER")))
		.andExpect(status().is3xxRedirection())
		.andExpect(model().hasNoErrors())
		.andExpect(redirectedUrl("/user"));
	}
	
	@Test
	public void testCommentBlogView() throws Exception{
		this.mockMvc.perform(get("/user/commentBlog/{blogUrl}","testBlogUrl")
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(view().name(containsString("users/manages/user-blog-comments")));
		
	}
	
	@Test
	public void testPostBlogView() throws Exception{
		this.mockMvc.perform(get("/user/post/{blogUrl}","testBlogUrl")
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(view().name(containsString("users/manages/user-blog-posts")));
		
	}
	
	@Test
	public void testAddPostBlog() throws Exception{
		this.mockMvc.perform(get("/user/addPost")
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(model().attribute("location", "addPost"))
						.andExpect(view().name(containsString("users/manages/user-blog-posts-add")));
		
	}
	
	@Test
	public void testUpdateUserPasswordSuccess() throws Exception{
		this.mockMvc.perform(post("/user/updateUserPassword")
								.header("X-Requested-With", "XMLHttpRequest")
								.param("oldPassword", "123")
								.param("newPassword", "123")
								.param("newConfirmPassword", "123")
								.with(csrf())
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(content().string("{\"updatePasswordResult\":\"Parolan baþarýyla güncellendi.\","
								+ "\"status\":\"success\"}"));
		
	}
	
	@Test
	public void testUpdateUserPasswordError() throws Exception{
		this.mockMvc.perform(post("/user/updateUserPassword")
								.header("X-Requested-With", "XMLHttpRequest")
								.param("oldPassword", "123")
								.param("newPassword", "123")
								.param("newConfirmPassword", "12")
								.with(csrf())
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(content().string("{\"errorNewConfirmPassword\":\"Yeni parola ile yeni parola tekrarý eþleþmiyor!\","
								+ "\"status\":\"fail\"}"));
		
	}
	
	@Test
	public void testUpdateUserInformationSuccess() throws Exception{
		this.mockMvc.perform(post("/user/updateUserInformation")
								.header("X-Requested-With", "XMLHttpRequest")
								.param("firstName", "Eyüp")
								.param("lastName", "Gevenim")
								.param("email", "e@e.com")
								.with(csrf())
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(content().string("{\"updateUserInformationResult\":\"Kullanýcý bilgileri güncellendi.\","
								+ "\"status\":\"success\"}"));
		
	}
	
	@Test
	public void testUpdateUserInformationError() throws Exception{
		this.mockMvc.perform(post("/user/updateUserInformation")
								.header("X-Requested-With", "XMLHttpRequest")
								.param("firstName", "")
								.param("lastName", "Gevenim")
								.param("email", "e@e.com")
								.with(csrf())
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk())
						.andExpect(content().string("{\"errorFirstName\":\"Ad zorunludur!\","
								+ "\"status\":\"fail\"}"));
		
	}
	
	
	@Test
	public void testGetUserMenu() throws Exception{
		this.mockMvc.perform(post("/user/getUserMenu")
								.header("X-Requested-With", "XMLHttpRequest")
								.with(csrf())
								.with(user("e@e.com").roles("USER")))
						.andExpect(status().isOk());
						//.andExpect(content().string());//...
		
	}
}
