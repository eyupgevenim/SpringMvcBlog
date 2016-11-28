package com.springframework.mvc.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class UserValidationTests {

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(new UserValidation()).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void validateSuccess() throws Exception {
		this.mockMvc.perform(get("/validate")
				.param("UserName", "test")
				.param("FirstName", "Eyup")
				.param("LastName", "Gevenim")
				.param("Email", "test@test.com")
				.param("Password", "123")
				.param("ConfirmPassword", "123"))
				.andExpect(content().string("No errors"));
	}

	@Test
	public void validateErrors() throws Exception {
		this.mockMvc.perform(get("/validate")
				.param("UserName", "test")
				.param("FirstName", "Eyup")
				.param("LastName", "Gevenim")
				.param("Email", "test.com")
				.param("Password", "123")
				.param("ConfirmPassword", "123"))
				.andExpect(content().string("Object has validation errors"));
	}

}
