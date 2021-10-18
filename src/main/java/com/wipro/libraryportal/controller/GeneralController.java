package com.wipro.libraryportal.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.libraryportal.dto.UserDto;
import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.service.ApplicationService;
import com.wipro.libraryportal.service.BookService;
import com.wipro.libraryportal.service.IssueService;
import com.wipro.libraryportal.service.UserService;




@Controller
public class GeneralController {
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	IssueService issueService;
	
	private static final String SESSION_KEY_USERNAME = "USERNAME";
	private static final String SIGNUP_PAGE = "signup";
	private static final String LOGIN_PAGE = "login";
	private static final String MESSAGE = "message";
	
	
	
	@GetMapping(value="/")
	public String redirectToLogin() {
		return "redirect:login";
	}
	
	@GetMapping(value="signup")
	public String showSignupPage(ModelMap model) {
		model.put("user", new User("", ""));
		return SIGNUP_PAGE;
	}
	
	@PostMapping("signup")
	public String register(@ModelAttribute("user") UserDto userx, ModelMap model) {
		
		/* 
		 	Check for email validity
		  	redirect to sign-up page again if found invalid
		*/
		if(!applicationService.isValidEmail(userx.getEmail())) {
			model.addAttribute(MESSAGE, "Invalid email provided!");
			return SIGNUP_PAGE;
		}
		
		/*
		 	Creating password hash to store into db
		*/
		String pwdHash = applicationService.getHash(userx.getPassword());
		
		if(userService.registerUser(new User(userx.getEmail().toLowerCase(), pwdHash))) {
			/* 
			 	User registered successfully
				redirecting to login page
			*/
			model.addAttribute(MESSAGE, "User registered successfully. Sign in to access portal");
			return LOGIN_PAGE;
		}
		
		
		// Something went wrong, user didn't got registered
		model.addAttribute(MESSAGE, "Username already taken!");
		return SIGNUP_PAGE;
	}
	
	@GetMapping(value="login")
	public String showLoginPage() {
		return LOGIN_PAGE;
	}
	
	@PostMapping("login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap model, HttpServletRequest req) {
		/*
		 	if the user is invalid, redirect to login
		 	else, create session and redirect to home page
		 */
		if(!userService.isValidUser(email, password)) {
			model.addAttribute(MESSAGE, "Invalid credential provided!");
			return LOGIN_PAGE;
		}
		
		req.getSession().setAttribute(SESSION_KEY_USERNAME, email);
		req.getSession().setAttribute("ADMIN", userService.isAdmin(email));
		return "redirect:welcome";
	}
	
	@GetMapping("welcome")
	public String showWelcomePage(ModelMap model) {
		model.addAttribute("booksList", bookService.getAllBooks());
		return "welcome";
	}
	
	
	@GetMapping("my-issues")
	public String showMyIssuesPage(ModelMap model, HttpServletRequest req) {
		if(req.getSession().getAttribute(SESSION_KEY_USERNAME) != null){
			User user = userService.getUserByEmail((String) req.getSession().getAttribute(SESSION_KEY_USERNAME));
			model.addAttribute("myIssues", issueService.getMyIssues(user.getMemberId()));
			return "my-issues";
		}else {
			return LOGIN_PAGE;
		}
		
	}
	

}
