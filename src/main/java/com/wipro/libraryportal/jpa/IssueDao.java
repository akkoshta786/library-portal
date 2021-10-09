package com.wipro.libraryportal.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wipro.libraryportal.entity.Issue;

public interface IssueDao extends CrudRepository<Issue, String> {
	List<Issue> findByMemberId(long memberId);
	
	
}
