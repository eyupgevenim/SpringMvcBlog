package com.springframework.mvc.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.mvc.models.User;

@RestController
public class AjaxController {
	
	
	@RequestMapping(value = "/demo/a/b/c", method = RequestMethod.POST)
	public String Demo(@RequestBody User user){
		//String data="{'X':'"+a+"','Y':'"+b+"','Z':'"+c+"'}";
		String data = user.getPassword()+" "+user.getConfirmPassword();
		return data;
	}

}
