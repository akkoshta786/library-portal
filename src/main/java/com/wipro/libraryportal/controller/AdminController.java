package com.wipro.libraryportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/delete/{bookIsbn}")
	public void deleteBook(@PathVariable("bookIsbn") String isbn) {
		System.err.println(isbn);
	}
	
	@GetMapping("/issue/{toEmail}/{isbn}")
	@ResponseBody
	public int issueBook(@PathVariable("toEmail") String toEmail, @PathVariable("isbn") String isbn, HttpServletRequest req) {
		if((boolean) req.getSession().getAttribute("ADMIN")){
			User user = userService.getUserByEmail(toEmail);
			if(user != null) {
				long memberId = user.getMemberId();
				if(!issueService.checkBookAvailibilityWithMember(memberId, isbn)) {
					if(bookService.decreaseBookCopies(isbn)) {
						issueService.saveIssue(new Issue(memberId, isbn));
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
