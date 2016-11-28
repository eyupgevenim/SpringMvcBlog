package com.springframework.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "admin")
public class AdminController {
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login( Model model) {
		
		return "admin/login";
	}
	
	@RequestMapping(value = {"","/","panel"}, method = RequestMethod.GET)
	public String admin( Model model) {
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String adminUsers( Model model) {
		
		return "admin/admin-users";
	}
	
	
}
