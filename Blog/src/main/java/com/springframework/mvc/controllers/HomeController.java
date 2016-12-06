package com.springframework.mvc.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/","home","Home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		if(getRole().equals("ROLE_USER"))
			return "redirect:/user";
		else if(getRole().equals("ROLE_ADMIN"))
			return "redirect:/admin";
		
		
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("location", "home");
		
		return "home/home";
	}
	
	
	private String getRole(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (roles.contains("ROLE_USER")) {
            return "ROLE_USER";
        }else if (roles.contains("ROLE_ADMIN")) {
            return "ROLE_ADMIN";
        } else {
        	return "anonymousUser";
        }
	}
	
}
