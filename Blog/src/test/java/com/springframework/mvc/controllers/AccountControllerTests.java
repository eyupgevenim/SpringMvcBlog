package com.springframework.mvc.controllers;

import com.springframework.mvc.AbstractContextControllerTests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTests extends AbstractContextControllerTests {
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}
	
	@Test
	public void register() throws Exception {
		this.mockMvc.perform(get("/account/register"))
				.andExpect(view().name(containsString("users/account/register")));
				//.andExpect(model().attribute("user","eyup"));
	}
	
	
	@Test
	public void submitSuccess() throws Exception { 
		this.mockMvc.perform(
				post("/account/register")
					.param("UserName", "test")
					.param("FirstName", "Eyup")
					.param("LastName", "Gevenim")
					.param("Email", "test@test.com")
					.param("Password", "123")
					.param("ConfirmPassword", "123"))
				.andDo(print())
				.andExpect(redirectedUrl("/account/login"))
				.andExpect(flash().attribute("user","{'UserName':'test',FirstName:'Eyup','LastName':'Gevenim',"
						+ "'Email':'test@test.com','Password':'123','ConfirmPassword':'123'}"));
	}
	
	/*
	@Test
    public void testHandleLogin() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AccountController()).build();
        mockMvc.perform(post("/login").param("ssoId", "e@e.com").param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/account/login"));
    }
	*/
	

	
	
}
