package com.wipro.libraryportal.dto;

import java.util.Date;


public class UserBookDto {
	private long issueId;
	private String email;
	private String isbn;
	private String title;
	private Date dateOfIssue;
	private int duration;


	public UserBookDto(long issueId, String email, String isbn, String title, Date dateOfIssue, int duration) {
		super();
		this.issueId = issueId;
		this.email = email;
		this.isbn = isbn;
		this.title = title;
		this.dateOfIssue = dateOfIssue;
		this.duration = duration;
	}


	public long getIssueId() {
		return issueId;
	}


	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	@Override
	public String toString() {
		return "UserBookDto [issueId=" + issueId + ", email=" + email + ", isbn=" + isbn + ", title=" + title
				+ ", dateOfIssue=" + dateOfIssue + ", duration=" + duration + "]";
	}


	
	
}
