package com.wipro.libraryportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.jpa.IssueDao;

@Component
public class IssueService {
	@Autowired
	private IssueDao issueDao;
	
	public void saveIssue(Issue issue) {
		issueDao.save(issue);
	}
	
}
