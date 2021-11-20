package com.wipro.libraryportal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.libraryportal.entity.Book;
import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.jpa.BookDao;

import java.util.ArrayList;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    @Test
    void testAddBook() {
        when(this.bookDao.existsById((String) any())).thenReturn(true);

        Book book = new Book();
        book.setPublisher("Publisher1");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("english");
        book.setIsbn("Isbn");
        book.setTitle("Heads you win");
        book.setAuthor("Jeffery Archer");
        book.setCopies(1);
        book.setNumberOfPages(10);
        assertFalse(this.bookService.addBook(book));
        verify(this.bookDao).existsById((String) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testAddBook2() {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("en");
        book.setIsbn("Isbn");
        book.setTitle("Anybook");
        book.setAuthor("Anonymous");
        book.setCopies(1);
        book.setNumberOfPages(10);
        when(this.bookDao.save((Book) any())).thenReturn(book);
        when(this.bookDao.existsById((String) any())).thenReturn(false);

        Book book1 = new Book();
        book1.setPublisher("Publisher");
        book1.setIssues(new HashSet<Issue>());
        book1.setLanguage("en");
        book1.setIsbn("Isbn");
        book1.setTitle("The Blue Umbrella");
        book1.setAuthor("Ruskin Bond");
        book1.setCopies(1);
        book1.setNumberOfPages(10);
        assertTrue(this.bookService.addBook(book1));
        verify(this.bookDao).existsById((String) any());
        verify(this.bookDao).save((Book) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testUpdateBookCopies() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        when(this.bookDao.findByIsbn((String) any())).thenReturn(bookList);
        assertFalse(this.bookService.updateBookCopies("Isbn", 1));
        verify(this.bookDao).findByIsbn((String) any());
        assertEquals(bookList, this.bookService.getAllBooks());
    }

    @Test
    void testUpdateBookCopies2() {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("en");
        book.setIsbn("56611216949");
        book.setTitle("Monk who sold his ferrari");
        book.setAuthor("Robin Sharma");
        book.setCopies(1);
        book.setNumberOfPages(10);

        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        doNothing().when(this.bookDao).updateBookCopies((String) any(), anyInt());
        when(this.bookDao.findByIsbn((String) any())).thenReturn(bookList);
        assertTrue(this.bookService.updateBookCopies("Isbn", 1));
        verify(this.bookDao).findByIsbn((String) any());
        verify(this.bookDao).updateBookCopies((String) any(), anyInt());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testUpdateBookCopies3() {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("eng");
        book.setIsbn("Isbn");
        book.setTitle("Doctor");
        book.setAuthor("Danial");
        book.setCopies(Integer.MIN_VALUE);
        book.setNumberOfPages(10);

        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        doNothing().when(this.bookDao).updateBookCopies((String) any(), anyInt());
        when(this.bookDao.findByIsbn((String) any())).thenReturn(bookList);
        assertFalse(this.bookService.updateBookCopies("Isbn", 1));
        verify(this.bookDao).findByIsbn((String) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }

    @Test
    void testDeleteByIsbn() {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("en");
        book.setIsbn("7986451320195");
        book.setTitle("AutoBiography");
        book.setAuthor("Anonymous Author");
        book.setCopies(0);
        book.setNumberOfPages(10);

        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        doNothing().when(this.bookDao).delete((Book) any());
        when(this.bookDao.findByIsbn((String) any())).thenReturn(bookList);
        assertTrue(this.bookService.deleteByIsbn("Isbn"));
        verify(this.bookDao).delete((Book) any());
        verify(this.bookDao).findByIsbn((String) any());
        assertTrue(this.bookService.getAllBooks().isEmpty());
    }
}

