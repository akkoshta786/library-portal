package com.wipro.libraryportal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String isbn;
	
	@NotNull
	private String title;
	
	
	@NotNull
	private String publisher;
	
	@NotNull
	private String language;
	
	@NotNull
	private int numberOfPages;
	
	@NotNull
	@Column(columnDefinition = "integer default 0")
	private int status;
	
	@Column(nullable = true)
	private String issuedBy;


	public Book() {
		super();
	}
	

	public Book(@NotNull String iSBN, @NotNull String title, @NotNull String publisher,
			@NotNull String language, @NotNull int numberOfPages) {
		super();
		isbn = iSBN;
		this.title = title;
		this.publisher = publisher;
		this.language = language;
		this.numberOfPages = numberOfPages;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String iSBN) {
		isbn = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", language="
				+ language + ", numberOfPages=" + numberOfPages + "]";
	}

	
	
	
}
