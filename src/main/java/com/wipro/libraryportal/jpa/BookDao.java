package com.wipro.libraryportal.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wipro.libraryportal.entity.Book;

public interface BookDao extends CrudRepository<Book, String> {
	List<Book> findByIsbn(String isbn);
	
	@Transactional
	@Modifying
	@Query("update Book b set b.copies = :copies where b.isbn = :isbn")
	void updateBookCopies(@Param(value="isbn") String isbn, @Param(value="copies") int copies);
	
	
}
