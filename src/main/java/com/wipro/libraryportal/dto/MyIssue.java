package com.wipro.libraryportal.dto;

import java.util.Date;

public class MyIssue {
	private String isbn;
	private String title;
	private Date dateOfIssue;
	private int duration;
	
	
	public MyIssue(String isbn, String title, Date dateOfIssue, int duration) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.dateOfIssue = dateOfIssue;
		this.duration = duration;
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
		return "MyIssue [isbn=" + isbn + ", title=" + title + ", dateOfIssue=" + dateOfIssue + ", duration=" + duration
				+ "]";
	}


	
	
}
