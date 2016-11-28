package com.springframework.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BlogController{
	
	//GET: blog.com/blogName
	@RequestMapping(value = {"/{blogName}"})
	public String doGet(@PathVariable("blogName") String blogName, Model model) throws Exception{
		
		model.addAttribute("bn",blogName);
		model.addAttribute("mn","default:Home");
		model.addAttribute("tl","title:null");
		
		 return "blogs/blog";
	}
	
	//GET: blog.com/blogName/manuName
	@RequestMapping(value = {"/{blogName}/{menuName}"})
	public String doGet(@PathVariable("blogName") String blogName,
			@PathVariable("menuName") String menuName, Model model) throws Exception{
		
		model.addAttribute("bn",blogName);
		model.addAttribute("mn",menuName);
		model.addAttribute("tl","title:null");
		
		return "blogs/blog";
	}
	
	
	//GET: blog.com/blogName/manuName/sharingTitle
	@RequestMapping(value = "/{blogName}/{menuName}/{postTitle}", method = RequestMethod.GET)
	public String doGet(@PathVariable("blogName") String blogName,
			@PathVariable("menuName") String menuName,
			@PathVariable("postTitle") String postTitle, Model model) throws Exception{
		
		model.addAttribute("bn",blogName);
		model.addAttribute("mn",menuName);
		model.addAttribute("tl",postTitle);
		
		return "blogs/blog-sharing";
	}

	
}
