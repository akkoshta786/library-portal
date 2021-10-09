package com.wipro.libraryportal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "issues")
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long issueId;
	
	@NotNull
	private long memberId;
	
	@NotNull
	private String isbn;
	
	private Date dateOfIssue;


	public Issue() {
		super();
	}


	public Issue(@NotNull long memberId, @NotNull String isbn) {
		super();
		this.memberId = memberId;
		this.isbn = isbn;
		this.dateOfIssue = new Date();
	}


	public long getIssueId() {
		return issueId;
	}


	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}


	public long getMemberId() {
		return memberId;
	}


	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public Date getDateOfIssue() {
		return dateOfIssue;
	}


	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}


	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", memberId=" + memberId + ", isbn=" + isbn + ", dateOfIssue="
				+ dateOfIssue + "]";
	}
	
	
}
