package com.wipro.libraryportal.dto;

import java.util.Date;


public class UserBookDto {
	private String email;
	private String isbn;
	private String title;
	private Date dateOfIssue;


	public UserBookDto(String email, String isbn, String title, Date dateOfIssue) {
		super();
		this.email = email;
		this.isbn = isbn;
		this.title = title;
		this.dateOfIssue = dateOfIssue;
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


	@Override
	public String toString() {
		return "UserBookDto [email=" + email + ", isbn=" + isbn + ", title=" + title + ", dateOfIssue=" + dateOfIssue
				+ "]";
	}

	
	
	
}
