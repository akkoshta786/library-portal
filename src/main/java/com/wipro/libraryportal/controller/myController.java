package com.wipro.libraryportal.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.service.ApplicationService;


@Controller
public class myController {
	
	@Autowired
	ApplicationService service;
	
	
	
	@RequestMapping(value="/")
	public String redirectToLogin() {
		return "redirect:/login";
	}
	
	@GetMapping(value="/signup")
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
	
	@GetMapping(value="/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest req) {
		if(service.isValidUser(email, password)) {
			req.getSession().setAttribute("USERNAME", email);
			return "redirect:/welcome";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("welcome")
	private String showWelcomePage() {
		return "welcome";
	}

}
