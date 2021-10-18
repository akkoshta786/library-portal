package com.wipro.libraryportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testMethod1() {
        User actualUser = new User();
        actualUser.setAdmin(true);
        actualUser.setEmail("my@example.com");
        HashSet<Issue> issueSet = new HashSet<Issue>();
        actualUser.setIssues(issueSet);
        actualUser.setMemberId(123L);
        actualUser.setPassword("Hello123");
        assertEquals("my@example.com", actualUser.getEmail());
        assertSame(issueSet, actualUser.getIssues());
        assertEquals(123L, actualUser.getMemberId().longValue());
        assertEquals("Hello123", actualUser.getPassword());
        assertTrue(actualUser.isAdmin());
        assertEquals("User [email=my@example.com]", actualUser.toString());
    }

    @Test
    void testMethod2() {
        User actualUser = new User("my@example.com", "Hello123");
        actualUser.setAdmin(true);
        actualUser.setEmail("my@example.com");
        HashSet<Issue> issueSet = new HashSet<Issue>();
        actualUser.setIssues(issueSet);
        actualUser.setMemberId(123L);
        actualUser.setPassword("Hello123");
        assertEquals("my@example.com", actualUser.getEmail());
        assertSame(issueSet, actualUser.getIssues());
        assertEquals(123L, actualUser.getMemberId().longValue());
        assertEquals("Hello123", actualUser.getPassword());
        assertTrue(actualUser.isAdmin());
        assertEquals("User [email=my@example.com]", actualUser.toString());
    }

    @Test
    void testMethod3() {
        User actualUser = new User("my@example.com", "Hello123", true);
        actualUser.setAdmin(true);
        actualUser.setEmail("my@example.com");
        HashSet<Issue> issueSet = new HashSet<Issue>();
        actualUser.setIssues(issueSet);
        actualUser.setMemberId(123L);
        actualUser.setPassword("Hello123");
        assertEquals("my@example.com", actualUser.getEmail());
        assertSame(issueSet, actualUser.getIssues());
        assertEquals(123L, actualUser.getMemberId().longValue());
        assertEquals("Hello123", actualUser.getPassword());
        assertTrue(actualUser.isAdmin());
        assertEquals("User [email=my@example.com]", actualUser.toString());
    }
}

