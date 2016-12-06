package com.springframework.mvc.controllers;

import com.springframework.mvc.AbstractContextControllerTests;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/spring-security.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTests extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;

	@Before
	public void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders
							.webAppContextSetup(context)
							.apply(springSecurity())
							.build();
	}
	
	@Test
	public void testHomeView() throws Exception {
		mockMvc.perform(get("/home"))
						.andExpect(status().isOk())
						.andExpect(view().name(containsString("home/home")));
	}
	
}
