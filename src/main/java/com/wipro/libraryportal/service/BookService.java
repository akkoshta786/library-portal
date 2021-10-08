package com.wipro.libraryportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.Book;
import com.wipro.libraryportal.jpa.BookDao;

@Component
public class BookService {
	@Autowired
	private BookDao bookDao;
	
	public List<Book> getAllBooks(){
		return (List<Book>) bookDao.findAll();
	}
	
	public boolean addBook(Book book) {
		if(bookDao.existsById(book.getIsbn())) {
			return false;
		}
		bookDao.save(book);
		return true;
	}
}
