package com.wipro.libraryportal.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Book {
	@Id
	private String isbn;
	
	@NotNull
	private String title;
	
	@NotNull
	private String author;
	
	@NotNull
	private String publisher;
	
	@NotNull
	private String language;
	
	@NotNull
	private int numberOfPages;
	
	@NotNull
	private int copies;
	
	@OneToMany(mappedBy = "book")
	private Set<Issue> issues;

	public Book() {
		super();
	}
	

	public Book(String isbn, @NotNull String title, @NotNull String author, @NotNull String publisher,
			@NotNull String language, @NotNull int numberOfPages, @NotNull int copies) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.copies = copies;
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


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
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
	

	public int getCopies() {
		return copies;
	}


	public void setCopies(int copies) {
		this.copies = copies;
	}


	public Set<Issue> getIssues() {
		return issues;
	}


	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}


	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", language=" + language + ", numberOfPages=" + numberOfPages + ", copies=" + copies + "]";
	}
	
	
}
