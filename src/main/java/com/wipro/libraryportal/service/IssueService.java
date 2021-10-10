package com.wipro.libraryportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.dto.MyIssue;
import com.wipro.libraryportal.dto.UserBookDto;
import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.jpa.IssueDao;

@Component
public class IssueService {
	@Autowired
	private IssueDao issueDao;
	
	public boolean saveIssue(Issue issue) {
		issueDao.save(issue);
		return true;
	}
	
	public List<UserBookDto> getAllIssues(){
		return issueDao.fetchAllIssuesJoin();
	}
	
	public boolean checkBookAvailibilityWithMember(long memberId, String isbn) {
		return !issueDao.findIssueByEmailAndIsbn(memberId, isbn).isEmpty();
	}
	
	public List<MyIssue> getMyIssues(long memberId){
		return issueDao.findMyIssues(memberId);
	}
}
