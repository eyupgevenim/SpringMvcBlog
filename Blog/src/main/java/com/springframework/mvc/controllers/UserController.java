package com.springframework.mvc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springframework.mvc.dao.BlogDao;
import com.springframework.mvc.dao.CategoryDao;
import com.springframework.mvc.dao.PostDao;
import com.springframework.mvc.dao.UserDao;
import com.springframework.mvc.models.Blog;
import com.springframework.mvc.models.Comment;
import com.springframework.mvc.models.Menu;
import com.springframework.mvc.models.Post;
import com.springframework.mvc.models.User;
import com.springframework.mvc.validation.BlogValidation;


@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	UserDao userDAO;
	
	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	CategoryDao categoryDAO;
	
	@Autowired
	PostDao postDAO;
	
	@Autowired
	private BlogValidation blogValidator;
	
	
	@RequestMapping(value = {"","/","panel"}, method = RequestMethod.GET)
	public String user( Model model) {
		model.addAttribute("listBlog",blogDAO.getUserBlogs(getUser().getEmail()));
		
		return "users/manages/user";
	}
	
	@RequestMapping(value = "setting", method = RequestMethod.GET)
	public String setting( Model model) {
		
		User user = getUser();
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("email", user.getEmail());
		
		return "users/manages/user-settings";
	}
	
	@RequestMapping(value = "newBlog", method = RequestMethod.GET)
	public String newBlog( Model model) {
		
		model.addAttribute("blog", new Blog());
		model.addAttribute("category", categoryDAO.findAllCategory());
		return "users/manages/user-new-blog";
	}
	
	@RequestMapping(value = "newBlog", method = RequestMethod.POST)
	public String newBlog(@Valid Blog blog, BindingResult result, Model model) {
		
		
		blogValidator.validate(blog, result);
		if(result.hasErrors()){
			model.addAttribute("blog", blog);
			model.addAttribute("category", categoryDAO.findAllCategory());
			return "users/manages/user-new-blog";
		}
		
		blog.setUser(getUser());
		if(blogDAO.saveBlog(blog)){
			//this have to edit
			return "redirect:/user";
		} else {
			setUserMenus(model);
			model.addAttribute("error", "Kayýt baþarsýz !");
			model.addAttribute("blog", blog);
			model.addAttribute("category", categoryDAO.findAllCategory());
			return "users/manages/user-new-blog";
		}
		
	}
	
	@RequestMapping(value = "settingsBlog/{blogUrl}", method = RequestMethod.GET)
	public String settingsBlog(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception {
		
		List <Menu> blogMenus = blogDAO.getBlogsMenus(blogUrl);
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		model.addAttribute("blogMenus",blogMenus);
		model.addAttribute("menu", new Menu());
		//model.addAttribute("menu",blogDAO.getAllMenus());
		model.addAttribute("menu",blogDAO.getUndefineAllMenus(blogMenus));
		
		model.addAttribute("location", "settingsBlog");
		
		return "users/manages/user-blog-settings";
	}
	
	@RequestMapping(value = "settingsBlog/{blogUrl}", method = RequestMethod.POST)
	public String settingsBlog(HttpServletRequest request, @PathVariable("blogUrl") String blogUrl, 
			Model model) throws Exception {
		
		Menu menu = new Menu();
		menu.setId(Integer.valueOf(request.getParameter("Id")));
		
		Blog blog = blogDAO.findBlogByBlogUrl(blogUrl);
		menu.setBlogId(blog.getId());
		
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(menu);
		
		if(blogDAO.addBlogMenus(menus)){
			return "redirect:/user/settingsBlog/"+blogUrl;
		}
		
		model.addAttribute("location", "settingsBlog");
		
		return "redirect:/user/settingsBlog/"+blogUrl;
	}
	
	@RequestMapping(value = "commentBlog/{blogUrl}", method = RequestMethod.GET)
	public String commentBlog(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception {
		model.addAttribute("blogUrl",blogUrl);
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		
		return "users/manages/user-blog-comments";
	}
	
	@ResponseBody
	@RequestMapping(value = "getBlogComments", method = RequestMethod.POST)
	public List<Comment> getBlogComments(HttpServletRequest request, HttpServletResponse response){
		String blogUrl = request.getParameter("blogUrl");
		
		return postDAO.getBlogComents(blogUrl);
	}
	
	@RequestMapping(value = "/post/{blogUrl}", method = RequestMethod.GET)
	public String post(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception {
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		//model.addAttribute("listPost",postDAO.getBlogPosts(blogUrl));
		model.addAttribute("listPost",postDAO.getBlogPosts("Anasayfa", blogUrl));
		return "users/manages/user-blog-posts";
	}
	
	@RequestMapping(value = "addPost/{blogUrl}", method = RequestMethod.GET)
	public String addPost(@PathVariable("blogUrl") String blogUrl, Model model) {
		
		model.addAttribute("blog",blogDAO.findBlogByBlogUrl(blogUrl));
		model.addAttribute("location", "addPost");
		return "users/manages/user-blog-posts-add";
	}
	
	@RequestMapping(value = "addPost/{blogUrl}", method = RequestMethod.POST)
	public String addPost(@PathVariable("blogUrl") String blogUrl, HttpServletRequest request, Model model) {
		
		Post post = new Post();
		Menu menu = blogDAO.getMenuByMenuName("Anasayfa",blogUrl);
		post.setPostContent(request.getParameter("post-content"));
		post.setPostTite(request.getParameter("post-title"));
		post.setRequestMapping(setRequestMapping(request.getParameter("post-title")));
		post.setBlogMenuId(menu.getId());
		
		if(postDAO.savePost(post)){
			return "redirect:/user/post/"+blogUrl;
		}
		return "redirect:/user/addPost/"+blogUrl;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUserPassword", method = RequestMethod.POST)
	public Map<String,String> updateUserPassword(HttpServletRequest request, HttpServletResponse response){
		
		User user = getUser();
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("status", "success");
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newConfirmPassword = request.getParameter("newConfirmPassword");
		if(oldPassword.equals("")) {
			obj.put("status", "fail");
			obj.put("errorOldPassword", "Eski parola zorunludur!");
		} else if(!userDAO.isConfirmOldPassword(user.getEmail(), oldPassword)) {
			obj.put("status", "fail");
			obj.put("errorOldPassword", "Eski parola yanlýþ!");
		}
		
		if (newPassword.equals("")) {
			obj.put("status", "fail");
			obj.put("errorNewPassword", "Yeni parola zorunludur!");
		} else if (newConfirmPassword.equals("")) {
			obj.put("status", "fail");
			obj.put("errorNewConfirmPassword", "Yeni parola tekrarý zorunludur!");
		} else if (!newPassword.equals(newConfirmPassword)) {
			obj.put("status", "fail");
			obj.put("errorNewConfirmPassword", "Yeni parola ile yeni parola tekrarý eþleþmiyor!");
		}
		
		if(obj.get("status") == "success"){
			if(userDAO.updatePassword(newPassword,user.getEmail())){
				obj.put("status", "success");
				obj.put("updatePasswordResult", "Parolan baþarýyla güncellendi.");
			} else {
				obj.put("status", "fail");
				obj.put("updatePasswordResult", "Parola güncelleme sýrasýnda bir hatayla karþýlaþtý!");
			}
		}
		
		return obj;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUserInformation", method = RequestMethod.POST)
	public Map<String,String> updateUserInformation(HttpServletRequest request, HttpServletResponse response){
		User user = getUser();
		User u = new User();
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("status", "success");
		
		u.setId(user.getId());
		//u.setUserName(request.getParameter("userName"));
		u.setFirstName(request.getParameter("firstName"));
		u.setLastName(request.getParameter("lastName"));
		u.setEmail(request.getParameter("email"));
		
		/*
		if(u.getUserName().equals("")){
			obj.put("status", "fail");
			obj.put("errorUserName", "Kullanýcý adý zorunludur!");
		}
		*/
		
		if(u.getFirstName().equals("")){
			obj.put("status", "fail");
			obj.put("errorFirstName", "Ad zorunludur!");
		}
		if(u.getLastName().equals("")){
			obj.put("status", "fail");
			obj.put("errorLastName", "Soyad zorunludur!");
		}
		if(u.getEmail().equals("")){
			obj.put("status", "fail");
			obj.put("errorEmail", "Email zorunludur!");
		} else if(userDAO.isThereSameEmailOutsideOfOwn(u)) {
			obj.put("status", "fail");
			obj.put("errorEmail", "Baþka email deneyin!");
		}
		
		if(obj.get("status") == "success"){
			if(userDAO.updateInformation(u)){
				obj.put("updateUserInformationResult", "Kullanýcý bilgileri güncellendi.");
			} else {
				obj.put("status", "fail");
				obj.put("updateUserInformationResult", "Güncelleme baþarsýz !");
			}
		}
		
		return obj;
	}
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); //get logged in username
	    User user = userDAO.findByEmail(email);
		return user;
	}
	
	private void setUserMenus(Model model){
		User user = getUser();
		model.addAttribute("userFullName", user.getFirstName()+" "+user.getLastName());
		model.addAttribute("listBlog", blogDAO.getUserBlogs(user.getEmail()));
	}
	
	private String setRequestMapping(String title){
		
		title = title.replaceAll("ð", "g")
				.replaceAll("þ", "s")
				.replaceAll("ý", "i")
				.replaceAll("ü", "u")
				.replaceAll("ö", "o")
				.replaceAll("ç", "c")
				.replaceAll("Ð", "G")
				.replaceAll("Þ", "S")
				.replaceAll("Ý", "I")
				.replaceAll("Ü", "U")
				.replaceAll("Ö", "O")
				.replaceAll("Ç", "C");
				//.replaceAll(" ", "-");
		title = title.replaceAll("[^a-zA-Z0-9_-]", "-");//'a-zA-Z0-9_-' haricindeki herþeyi '-' yap
		title = title.replaceAll("-*$", "");//sondaki tüm '-' temizle
		//title = title.replaceAll("-{2,80}", "-");//birden 80 e kadarki '-' larý bir taneye çeviri
		title = title.replaceAll("[-]-*", "-"); //birden fazla '-' larý bir taneye çeviri
		return title;
	}
	
	@ResponseBody
	@RequestMapping(value = "getUserMenu", method = RequestMethod.POST)
	public  Map<String,Object> getUserMenu(HttpServletRequest request, HttpServletResponse response){
		
		User user = getUser();
		
		Map<String,Object> obj = new HashMap<String,Object>();
		User u = new User();
		
		u.setUserName(user.getUserName());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		
		obj.put("user",u);
		obj.put("listBlog", blogDAO.getUserBlogs(user.getEmail()));
		
		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value = "removeMenu", method = RequestMethod.POST)
	public boolean RemoveMenu(HttpServletRequest request, HttpServletResponse response){
		String menuId = request.getParameter("menuId");
		String blogId = request.getParameter("blogId");
		return blogDAO.deleteMenu(Integer.valueOf(menuId), Integer.valueOf(blogId));
	}
	
	@ResponseBody
	@RequestMapping(value = "editMenuGet", method = RequestMethod.GET)
	public List<Post> EditMenuGet(HttpServletRequest request, HttpServletResponse response){
		String menuId = request.getParameter("menuId");
		String blogId = request.getParameter("blogId");
		//List<Post> post = postDAO.getBlogPosts(Integer.valueOf(menuId), Integer.valueOf(blogId));
		
		return postDAO.getBlogPosts(Integer.valueOf(menuId), Integer.valueOf(blogId));
	}
	
	@ResponseBody
	@RequestMapping(value = "editMenuSave", method = RequestMethod.POST)
	public boolean EditMenuSave(HttpServletRequest request, HttpServletResponse response){
		String menuId = request.getParameter("menuId");
		String blogId = request.getParameter("blogId");
		
		
		Post post = new Post();
		Menu menu = blogDAO.getMenuByMenuId(Integer.valueOf(menuId), Integer.valueOf(blogId));
		post.setPostContent(request.getParameter("postContent"));
		post.setPostTite(request.getParameter("postTitle"));
		post.setRequestMapping(setRequestMapping(request.getParameter("postTitle")));
		post.setBlogMenuId(menu.getId());
		
		List<Post> p = postDAO.getBlogPosts(Integer.valueOf(menuId), Integer.valueOf(blogId));
		
		if(p.size() > 0){
			post.setId(p.get(0).getId());
			return postDAO.updatePost(post);
		}
		
		return postDAO.savePost(post);
	}
	
}
