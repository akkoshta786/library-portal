package com.wipro.libraryportal.jpa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wipro.libraryportal.dto.MyIssue;
import com.wipro.libraryportal.dto.UserBookDto;
import com.wipro.libraryportal.entity.Issue;

public interface IssueDao extends CrudRepository<Issue, String> {
	@Query("SELECT i FROM Issue i WHERE i.isbn = ?1 AND i.status = 1")
	List<Issue> findAllIssuesByIsbn(String isbn);
	
	Optional<Issue> findByIssueId(long issueId);
	
	List<Issue> findByMemberId(long memberId);
	
	@Query("SELECT i FROM Issue i WHERE i.memberId = ?1 AND i.isbn = ?2 AND i.status = 1")
	List<Issue> findIssueByEmailAndIsbn(long memberId, String isbn);
	
	@Query("SELECT new com.wipro.libraryportal.dto.UserBookDto(i.issueId, u.email, i.isbn, b.title, i.dateOfIssue, i.returnDate) FROM User u JOIN Issue i ON u.memberId=i.memberId JOIN Book b ON i.isbn=b.isbn WHERE i.status = 1")
	List<UserBookDto> fetchAllIssuesJoin();
	
	@Query("SELECT new com.wipro.libraryportal.dto.MyIssue(i.isbn, b.title, i.dateOfIssue, i.returnDate) FROM Book b JOIN Issue i ON b.isbn=i.isbn WHERE i.memberId = :memberId AND i.status = 1")
	List<MyIssue> findMyIssues(@Param(value="memberId") long memberId); 
	
	@Transactional
	@Modifying
	@Query("UPDATE Issue i set i.status = 0 WHERE i.issueId = :issueId")
	void updateIssueStatus(@Param(value="issueId") long issueId);
	
}
