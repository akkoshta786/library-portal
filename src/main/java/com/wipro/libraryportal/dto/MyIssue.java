package com.wipro.libraryportal.dto;

import java.util.Date;

public class MyIssue {
	private String isbn;
	private String title;
	private Date dateOfIssue;
	private Date returnDate;
	
	
	public MyIssue(String isbn, String title, Date dateOfIssue, Date returnDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.dateOfIssue = dateOfIssue;
		this.returnDate = returnDate;
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


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	
	
}
