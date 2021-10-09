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
	
	public boolean decreaseBookCopies(String isbn) {
		List<Book> books = bookDao.findByIsbn(isbn);
		if(!books.isEmpty()) {
			System.err.println(books.get(0).getCopies());
			int currentCopies = books.get(0).getCopies();
			if(currentCopies > 0) {
				bookDao.updateBookCopies(isbn, currentCopies-1);
				return true;
			}
		}
		return false;
	}
}
