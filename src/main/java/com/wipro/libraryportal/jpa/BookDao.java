package com.wipro.libraryportal.jpa;

import org.springframework.data.repository.CrudRepository;

import com.wipro.libraryportal.entity.Book;

public interface BookDao extends CrudRepository<Book, String> {
	
}
