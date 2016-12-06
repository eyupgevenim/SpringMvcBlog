package com.springframework.mvc.controllers;

import com.springframework.mvc.AbstractContextControllerTests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/spring-security.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTests extends AbstractContextControllerTests {
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
		//this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		//this.mockMvc = standaloneSetup(new AccountController()).setViewResolvers(viewResolver).build();
		
	}
	
	@Test
	public void testRegisterViewPage() throws Exception {
		this.mockMvc.perform(get("/account/register"))
					.andExpect(status().isOk())
				//.andExpect(model().attribute("user",new User()))
				.andExpect(view().name(containsString("users/account/register")));
	}
	
	@Test
	public void testRegisterSubmitSuccess() throws Exception {
		
		MockHttpServletRequestBuilder createUser = post("/account/register")
				.header("X-Requested-With", "XMLHttpRequest")
				.param("UserName", "testRegister")
				.param("FirstName", "testFirstName")
				.param("LastName", "testLastName")
				.param("Email", "testRegister@test.com")
				.param("Password", "123")
				.param("ConfirmPassword", "123");
		this.mockMvc.perform(createUser)
						.andExpect(status().is3xxRedirection())
						.andExpect(model().hasNoErrors())
						.andExpect(redirectedUrl("login"));
	}
	
	@Test
	public void testRegisterSubmitError() throws Exception {
		
		MockHttpServletRequestBuilder createUser = post("/account/register")
				.param("UserName", "t")
				.param("FirstName", "testFirstName")
				.param("LastName", "testLastName")
				.param("Email", "testRegisterError@test.com")
				.param("Password", "123")
				.param("ConfirmPassword", "12");
		this.mockMvc.perform(createUser)
						.andExpect(status().isOk())
						.andExpect(model().hasErrors())
						.andExpect(model().errorCount(2))
						.andExpect(view().name(containsString("users/account/register")));
	}
	
	@Test
	public void testLoginViewPage() throws Exception {
		
		this.mockMvc.perform(get("/account/login"))
					.andExpect(status().isOk())
				.andExpect(view().name(containsString("users/account/login")));
	}
	
	@Test
	public void testUserLogin() throws Exception {
		
	    RequestBuilder requestBuilder = post("/login")
	    		.header("X-Requested-With", "XMLHttpRequest")
	            .param("ssoId", "e@e.com")
	            .param("password", "123");
	    
	    mockMvc.perform(requestBuilder)
	            //.andDo(print())
	            .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testLogoutViewPage() throws Exception {
		
		this.mockMvc.perform(get("/account/logout"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/account/login?logout=true"));
	}
	
}
