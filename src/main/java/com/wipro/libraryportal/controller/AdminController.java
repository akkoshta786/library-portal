package com.wipro.libraryportal.controller;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.libraryportal.entity.Book;
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
	
	@RequestMapping(value="postBook", method=RequestMethod.POST)
	@ResponseBody
	public int addBook(@RequestBody String json) {
		Object obj = JSONValue.parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		
		String isbn = (String) jsonObject.get("isbn");
		String title = (String) jsonObject.get("title");
		String author = (String) jsonObject.get("author");
		String publisher = (String) jsonObject.get("publisher");
		String language = (String) jsonObject.get("language");
		int numberOfPages = Integer.parseInt((String) jsonObject.get("numberOfPages"));
		int copies = Integer.parseInt((String) jsonObject.get("copies"));
		
		if(bookService.addBook(new Book(isbn, title, author, publisher, language, numberOfPages, copies))) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	
	@RequestMapping(value="issueBook", method = RequestMethod.POST)
	@ResponseBody
	public int issueBook(@RequestBody String json) {
		Object obj = JSONValue.parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		
		String toEmail = (String) jsonObject.get("email");
		String isbn = (String) jsonObject.get("isbn");
		int duration = Integer.parseInt((String) jsonObject.get("duration"));
		
		User user = userService.getUserByEmail(toEmail);
		if(user != null) {
			long memberId = user.getMemberId();
			if(!issueService.checkBookAvailibilityWithMember(memberId, isbn)) {
				if(bookService.updateBookCopies(isbn, -1)) {
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
	
	
	@GetMapping("all-issues")
	public String showAllIssuesPage(ModelMap model) {
		model.addAttribute("allIssuesList", issueService.getAllIssues());
		return "all-issues";
	}
	
	@RequestMapping(value="returnBook", method = RequestMethod.POST)
	@ResponseBody
	public int returnBook(@RequestBody String json) {
		Object obj = JSONValue.parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		
		long issueId = Long.parseLong((String) jsonObject.get("issueId"));
		Optional<Issue> issue = issueService.findIssueById(issueId);
		if(issue != null) {
			String isbn = issue.get().getIsbn();
			issueService.updateIssueStatus(issueId);
			bookService.updateBookCopies(isbn, 1);
		}
		return 1;
	}
	
}
