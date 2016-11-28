package com.springframework.mvc.controllers;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class BlogControllerTests  {
	
	@Test
	public void viewBlog() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogName", "ExampleBlogName");

		System.out.println(UriComponentsBuilder
				.fromPath("blog.com/{blogName}/home")
				.buildAndExpand(map).toUriString());
	}
	
	@Test
	public void viewBlogMenu() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogName", "ExampleBlogName");
		map.put("menu", "exampleMenuName");

		System.out.println(UriComponentsBuilder
				.fromPath("blog.com/{blogName}/{menu}")
				.buildAndExpand(map).toUriString());
	}

	@Test
	public void viewBlogPost() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogName", "ExampleBlogName");
		map.put("menu", "exampleMenuName");
		map.put("postTitle", "example-post-title");

		System.out.println(UriComponentsBuilder
				.fromPath("blog.com/{blogName}/{menu}/{postTitle}")
				.buildAndExpand(map).toUriString());
	}
}