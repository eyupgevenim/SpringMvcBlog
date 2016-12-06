package com.springframework.mvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.springframework.mvc.AbstractContextControllerTests;

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
	
	private Map<String, String> map;

	@Before
	public void setup() throws Exception {
		
		this.mockMvc = webAppContextSetup(this.wac).build();
		
		map = new HashMap<String, String>();
		map.put("blogName", "ExampleBlogName");
		map.put("menu", "exampleMenuName");
		map.put("postTitle", "example-post-title");
	}
	
	@Test
	public void testViewBlog() throws Exception {
		
		this.mockMvc.perform(get("/{blogName}", map.get("blogName")))
						.andExpect(model().attribute("bn", map.get("blogName")))
						.andExpect(model().attribute("mn", "default:Home"))
						.andExpect(model().attribute("tl", "title:null"))
						.andExpect(view().name(containsString("blogs/blog")));
		
		assertEquals(UriComponentsBuilder
				.fromPath("/{blogName}")
				.buildAndExpand(map).toUriString(), "/"+map.get("blogName"));
	}
	
	@Test
	public void testViewBlogMenu() throws Exception {
		
		this.mockMvc.perform(get("/{blogName}/{menu}",
									map.get("blogName"),map.get("menu")))
						.andExpect(model().attribute("bn", map.get("blogName")))
						.andExpect(model().attribute("mn", map.get("menu")))
						.andExpect(model().attribute("tl", "title:null"))
						.andExpect(view().name(containsString("blogs/blog")));
		
		assertEquals(UriComponentsBuilder
						.fromPath("/{blogName}/{menu}")
						.buildAndExpand(map).toUriString(), 
						"/"+map.get("blogName")+"/"+map.get("menu"));
	}

	@Test
	public void testViewBlogPost() throws Exception {
		
		this.mockMvc.perform(get("/{blogName}/{menu}/{postTitle}", 
									map.get("blogName"),map.get("menu"),map.get("postTitle")))
						.andExpect(model().attribute("bn", map.get("blogName")))
						.andExpect(model().attribute("mn", map.get("menu")))
						.andExpect(model().attribute("tl", map.get("postTitle")))
						.andExpect(view().name(containsString("blogs/blog-sharing")));
		
		assertEquals(UriComponentsBuilder
						.fromPath("/{blogName}/{menu}/{postTitle}")
						.buildAndExpand(map).toUriString(), 
						"/"+map.get("blogName")+"/"+map.get("menu")+"/"+map.get("postTitle"));
	}
	
}