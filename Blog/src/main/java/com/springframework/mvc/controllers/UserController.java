package com.springframework.mvc.controllers;

import java.util.HashMap;
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
import com.springframework.mvc.dao.UserDao;
import com.springframework.mvc.models.Blog;
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
	private BlogValidation blogValidator;
	
	
	@RequestMapping(value = {"","/","panel"}, method = RequestMethod.GET)
	public String user( Model model) {
		
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
	
	@RequestMapping(value = "commentBlog/{blogUrl}", method = RequestMethod.GET)
	public String commentBlog(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception {
		
		return "users/manages/user-blog-comments";
	}
	
	@RequestMapping(value = "/post/{blogUrl}", method = RequestMethod.GET)
	public String post(@PathVariable("blogUrl") String blogUrl, Model model) throws Exception {
		
		return "users/manages/user-blog-posts";
	}
	
	@RequestMapping(value = "addPost", method = RequestMethod.GET)
	public String addPost( Model model) {
		
		model.addAttribute("location", "addPost");
		return "users/manages/user-blog-posts-add";
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
	
}
