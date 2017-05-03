package com.springframework.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.mvc.dao.BlogDao;
import com.springframework.mvc.dao.PostDao;
import com.springframework.mvc.models.Comment;
import com.springframework.mvc.models.Post;


@Controller
public class BlogController{
	
	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	PostDao postDAO;
	
	//GET: blog.com/blogName
	@RequestMapping(value = {"/{blogUrl}"})
	public String doGet(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception{
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		model.addAttribute("blogMenus",blogDAO.getBlogsMenus(blogUrl));
		//model.addAttribute("listPost",postDAO.getBlogPosts(blogUrl));
		model.addAttribute("listPost",postDAO.getBlogPosts("Anasayfa", blogUrl));
		
		
		 return "blogs/blog";
	}
	
	//GET: blog.com/blogName/manuName
	@RequestMapping(value = {"/{blogUrl}/{menuName}"})
	public String doGet(@PathVariable("blogUrl") String blogUrl,
			@PathVariable("menuName") String menuName, Model model) throws Exception{
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		model.addAttribute("blogMenus",blogDAO.getBlogsMenus(blogUrl));
		
		if(menuName.equals("Anasayfa")){
			model.addAttribute("listPost",postDAO.getBlogPosts("Anasayfa", blogUrl));
			//model.addAttribute("listPost",postDAO.getBlogPosts(blogUrl));
			return "blogs/blog";
		}
		
		Post post = postDAO.getBlogPost(menuName, blogUrl);
		if(post != null)
			model.addAttribute("post",post);
		else
			model.addAttribute("post","");
		return "blogs/other-page";
	}
	
	
	//GET: blog.com/blogName/manuName/sharingTitle
	@RequestMapping(value = "/{blogUrl}/{menuName}/{requestMapping}", method = RequestMethod.GET)
	public String doGet(@PathVariable("blogUrl") String blogUrl,
			@PathVariable("menuName") String menuName,
			@PathVariable("requestMapping") String requestMapping, Model model) throws Exception{
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		model.addAttribute("blogMenus",blogDAO.getBlogsMenus(blogUrl));
		
		model.addAttribute("post",postDAO.getPost(requestMapping));
		
		model.addAttribute("comment", new Comment());
		
		model.addAttribute("postComments", postDAO.getComents(requestMapping));
		
		
		return "blogs/blog-sharing";
	}
	
	
	@RequestMapping(value = "/{blogUrl}/{requestMapping}/addComment", method = RequestMethod.POST)
	public String doPost(@PathVariable("blogUrl") String blogUrl,
			@PathVariable("requestMapping") String requestMapping, 
			Comment comment,Model model) throws Exception{
		
		Post post =postDAO.getPost(requestMapping);
		comment.setPostId(post.getId());
		postDAO.saveVisitorAndComment(comment);
		
		return "redirect:/"+blogUrl+"/Anasayfa/"+requestMapping;
	}

	
}
