package com.wipro.libraryportal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long issueId;
	
	@NotNull
	private long memberId;
	
	@NotNull
	private String isbn;
	
	private Date dateOfIssue;
	
	@NotNull
	private int duration;

	@ManyToOne
	@JoinColumn(name = "memberId", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "isbn", insertable = false, updatable = false)
	private Book book;
	
	public Issue() {
		super();
	}


	public Issue(@NotNull long memberId, @NotNull String isbn, @NotNull int duration) {
		super();
		this.memberId = memberId;
		this.isbn = isbn;
		this.dateOfIssue = new Date();
		this.duration = duration;
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

	
	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", memberId=" + memberId + ", isbn=" + isbn + ", dateOfIssue="
				+ dateOfIssue + "]";
	}
	
	
}
