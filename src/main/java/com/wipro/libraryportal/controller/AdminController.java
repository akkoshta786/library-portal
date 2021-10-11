package com.wipro.libraryportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.service.BookService;
import com.wipro.libraryportal.service.IssueService;
import com.wipro.libraryportal.service.UserService;


@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssueService issueService;
	
	
	@RequestMapping(value="issueBook", method = RequestMethod.POST)
	@ResponseBody
	public int issueBook(@RequestBody String json, HttpServletRequest req) {
		Object obj = JSONValue.parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		
		String toEmail = (String) jsonObject.get("email");
		String isbn = (String) jsonObject.get("isbn");
		int duration = Integer.parseInt((String) jsonObject.get("duration"));
		
		if((boolean) req.getSession().getAttribute("ADMIN")){
			User user = userService.getUserByEmail(toEmail);
			if(user != null) {
				long memberId = user.getMemberId();
				if(!issueService.checkBookAvailibilityWithMember(memberId, isbn)) {
					if(bookService.decreaseBookCopies(isbn)) {
						issueService.saveIssue(new Issue(memberId, isbn, duration));
						return 1;
					}else {
						return 2;
					}
				}else {
					return 3;
				}
			}else {
				return 4;
			}
		}
		
		return 0;
	}
	
	
	@GetMapping("all-issues")
	public String showAllIssuesPage(ModelMap model) {
		model.addAttribute("allIssuesList", issueService.getAllIssues());
		return "all-issues";
	}
	
}
