package com.wipro.libraryportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class BookTest {


    @Test
    void testConstructor1() {
        Book actualBook = new Book();
        actualBook.setAuthor("JaneDoe");
        actualBook.setCopies(1);
        actualBook.setIsbn("Isbn");
        HashSet<Issue> issueSet = new HashSet<Issue>();
        actualBook.setIssues(issueSet);
        actualBook.setLanguage("en");
        actualBook.setNumberOfPages(10);
        actualBook.setPublisher("Publisher");
        actualBook.setTitle("Life in a year");
        assertEquals("JaneDoe", actualBook.getAuthor());
        assertEquals(1, actualBook.getCopies());
        assertEquals("Isbn", actualBook.getIsbn());
        assertSame(issueSet, actualBook.getIssues());
        assertEquals("en", actualBook.getLanguage());
        assertEquals(10, actualBook.getNumberOfPages());
        assertEquals("Publisher", actualBook.getPublisher());
        assertEquals("Life in a year", actualBook.getTitle());
        assertEquals(
                "Book [isbn=Isbn, title=Life in a year, author=JaneDoe, publisher=Publisher, language=en, numberOfPages=10," + " copies=1]",
                actualBook.toString());
    }

    @Test
    void testConstructor2() {
        Book actualBook = new Book("Isbn", "Dr", "JaneDoe", "Publisher", "en", 10, 1);
        actualBook.setAuthor("JaneDoe");
        actualBook.setCopies(1);
        actualBook.setIsbn("Isbn");
        HashSet<Issue> issueSet = new HashSet<Issue>();
        actualBook.setIssues(issueSet);
        actualBook.setLanguage("en");
        actualBook.setNumberOfPages(10);
        actualBook.setPublisher("Publisher");
        actualBook.setTitle("Dr");
        assertEquals("JaneDoe", actualBook.getAuthor());
        assertEquals(1, actualBook.getCopies());
        assertEquals("Isbn", actualBook.getIsbn());
        assertSame(issueSet, actualBook.getIssues());
        assertEquals("en", actualBook.getLanguage());
        assertEquals(10, actualBook.getNumberOfPages());
        assertEquals("Publisher", actualBook.getPublisher());
        assertEquals("Dr", actualBook.getTitle());
        assertEquals(
                "Book [isbn=Isbn, title=Dr, author=JaneDoe, publisher=Publisher, language=en, numberOfPages=10," + " copies=1]",
                actualBook.toString());
    }
    @Test
    void testConstructor() {
        Book actualBook = new Book("Isbn", "Life in a year", "JaneDoe", "Publisher", "en", 10, 1);
        actualBook.setAuthor("JaneDoe");
        assertEquals("JaneDoe", actualBook.getAuthor());
    }
}

