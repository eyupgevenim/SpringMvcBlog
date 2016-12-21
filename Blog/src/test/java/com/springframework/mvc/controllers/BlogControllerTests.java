package com.springframework.mvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.dao.BlogDao;
import com.springframework.mvc.dao.PostDao;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlogControllerTests  extends AbstractContextControllerTests {
	
	private MockMvc mockMvc;
	

	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	PostDao postDAO;
	
	private Map<String, String> map;

	@Before
	public void setup() throws Exception {
		
		this.mockMvc = webAppContextSetup(this.wac).build();
		
		map = new HashMap<String, String>();
		map.put("blogUrl", "eyupgevenim");
		map.put("menu", "Anasayfa");
		map.put("requestMapping", "Test-Title");
	}
	
	@Test
	public void testViewBlog() throws Exception {
		
		this.mockMvc.perform(get("/{blogUrl}", map.get("blogUrl")))
						.andExpect(view().name(containsString("blogs/blog")));
		
		assertEquals(UriComponentsBuilder
				.fromPath("/{blogUrl}")
				.buildAndExpand(map).toUriString(), "/"+map.get("blogUrl"));
	}
	
	@Test
	public void testViewBlogMenu() throws Exception {
		
		this.mockMvc.perform(get("/{blogUrl}/{menu}",
									map.get("blogUrl"),map.get("menu")))
						.andExpect(view().name(containsString("blogs/blog")));
		
		assertEquals(UriComponentsBuilder
						.fromPath("/{blogUrl}/{menu}")
						.buildAndExpand(map).toUriString(), 
						"/"+map.get("blogUrl")+"/"+map.get("menu"));
	}

	@Test
	public void testViewBlogPost() throws Exception {
		
		this.mockMvc.perform(get("/{blogUrl}/{menu}/{requestMapping}", 
									map.get("blogUrl"),map.get("menu"),map.get("requestMapping")))
						.andExpect(view().name(containsString("blogs/blog-sharing")));
		
		assertEquals(UriComponentsBuilder
						.fromPath("/{blogUrl}/{menu}/{requestMapping}")
						.buildAndExpand(map).toUriString(), 
						"/"+map.get("blogUrl")+"/"+map.get("menu")+"/"+map.get("requestMapping"));
	}
	
}