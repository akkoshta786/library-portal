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
	
	public boolean updateBookCopies(String isbn, int operation) {
		List<Book> books = bookDao.findByIsbn(isbn);
		if(!books.isEmpty()) {
			int currentCopies = books.get(0).getCopies();
			if(operation + currentCopies >= 0) {
				bookDao.updateBookCopies(isbn, currentCopies+operation);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteByIsbn(String isbn) {
		bookDao.delete(bookDao.findByIsbn(isbn).get(0));
		return true;
	}
	
}
