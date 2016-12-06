package com.springframework.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.mvc.dao.UserDao;
import com.springframework.mvc.models.User;
import com.springframework.mvc.validation.UserValidation;

@Controller
@RequestMapping(value = "account")
public class AccountController {
	
	@Autowired
	private UserValidation userValidator;
	
	@Autowired
	UserDao userDAO;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		
		return "users/account/register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult result,Model model) {
		model.addAttribute("user",user);
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return "users/account/register";
		}
		
		if(userDAO.saveUser(user)){
			return "redirect:login";
		}else{
			model.addAttribute("errorRegister","Kayýt Baþarsýz !");
			return "users/account/register";
		}
		
	}
	

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "users/account/login";
	}

	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/account/login?logout=true";
	}
	
	/*
	@RequestMapping(value="accessDenied", method = RequestMethod.GET)
	public String accessDenied(){
		
		return "redirect:/account/login?error=true";
	}
	*/
	
}
