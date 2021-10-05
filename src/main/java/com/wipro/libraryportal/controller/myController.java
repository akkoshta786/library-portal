package com.wipro.libraryportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.service.ApplicationService;


@Controller
public class myController {
	
	@Autowired
	ApplicationService service;
	
	
	
	@RequestMapping(value="/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String showSignupPage(ModelMap model) {
		model.put("user", new User("", ""));
		return "signup";
	}
	
	@PostMapping("/signup")
	public String register(@ModelAttribute("user") User userx, ModelMap model) {
		String pwdHash = service.getHash(userx.getPassword());
		service.registerUser(new User(userx.getEmail(), pwdHash));
		
		return "redirect:/";
	}
}
