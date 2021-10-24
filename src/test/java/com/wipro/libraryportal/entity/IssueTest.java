package com.wipro.libraryportal.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class IssueTest {
    @Test
    void testConstructor() {
        Issue actualIssue = new Issue(123L, "Isbn", 1);

        assertNull(actualIssue.getBook());
        assertNull(actualIssue.getUser());
        assertEquals(1, actualIssue.getStatus());
        assertEquals(123L, actualIssue.getMemberId());
        assertEquals(0L, actualIssue.getIssueId());
        assertEquals("Isbn", actualIssue.getIsbn());
    }

    @Test
    void testingConstructor1() {
        Issue actualIssue = new Issue();
        Book book = new Book();
        book.setPublisher("Publisher1");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("eng");
        book.setIsbn("Isbn");
        book.setTitle("Monk who sold his ferrari");
        book.setAuthor("Robin Sharma");
        book.setCopies(1);
        book.setNumberOfPages(10);
        actualIssue.setBook(book);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualIssue.setDateOfIssue(fromResult);
        actualIssue.setIsbn("Isbn");
        actualIssue.setIssueId(123L);
        actualIssue.setMemberId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualIssue.setReturnDate(fromResult1);
        User user = new User();
        user.setMemberId(123L);
        user.setEmail("new@example.org");
        user.setPassword("1234");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);
        actualIssue.setUser(user);
        assertSame(book, actualIssue.getBook());
        assertSame(fromResult, actualIssue.getDateOfIssue());
        assertEquals("Isbn", actualIssue.getIsbn());
        assertEquals(123L, actualIssue.getIssueId());
        assertEquals(123L, actualIssue.getMemberId());
        assertSame(fromResult1, actualIssue.getReturnDate());
        assertEquals(0, actualIssue.getStatus());
        assertSame(user, actualIssue.getUser());
    }

    @Test
    void testingConstructor2() {
        Issue actualIssue = new Issue(123L, "Isbn", 1);

        assertEquals(1, actualIssue.getStatus());
        assertEquals(123L, actualIssue.getMemberId());
        assertEquals("Isbn", actualIssue.getIsbn());
    }

    @Test
    void testSetStatus() {
        Issue issue = new Issue(123L, "Isbn", 1);
        issue.setStatus(1);
        assertEquals(1, issue.getStatus());
    }
}

