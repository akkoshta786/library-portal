package com.wipro.libraryportal.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wipro.libraryportal.dto.MyIssue;
import com.wipro.libraryportal.dto.UserBookDto;
import com.wipro.libraryportal.entity.Issue;

public interface IssueDao extends CrudRepository<Issue, String> {
	List<Issue> findByMemberId(long memberId);
	
	@Query("SELECT i FROM Issue i WHERE i.memberId = ?1 AND i.isbn = ?2")
	List<Issue> findIssueByEmailAndIsbn(long memberId, String isbn);
	
	@Query("SELECT new com.wipro.libraryportal.dto.UserBookDto(u.email, i.isbn, b.title, i.dateOfIssue, i.duration) FROM User u JOIN Issue i ON u.memberId=i.memberId JOIN Book b ON i.isbn=b.isbn")
	List<UserBookDto> fetchAllIssuesJoin();
	
	@Query("SELECT new com.wipro.libraryportal.dto.MyIssue(i.isbn, b.title, i.dateOfIssue, i.duration) FROM Book b JOIN Issue i ON b.isbn=i.isbn WHERE i.memberId = :memberId")
	List<MyIssue> findMyIssues(@Param(value="memberId") long memberId); 
}
