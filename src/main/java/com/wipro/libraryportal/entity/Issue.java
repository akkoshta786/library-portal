package com.wipro.libraryportal.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
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
	private Date returnDate;
	
	@Column(nullable = false)
    private int status;

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
		this.returnDate = this.addDays(dateOfIssue, duration);
		this.status = 1;
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

	

	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
	
	private Date addDays(Date now, int days) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(now); 
		c.add(Calendar.DATE, days);
		return c.getTime();
	}


	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", memberId=" + memberId + ", isbn=" + isbn + ", dateOfIssue="
				+ dateOfIssue + "]";
	}
	
	
}
