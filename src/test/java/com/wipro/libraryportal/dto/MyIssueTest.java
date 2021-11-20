package com.wipro.libraryportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class MyIssueTest {
    @Test
    void testConstructor() {
        Date dateOfIssue = new Date(1L);
        MyIssue actualMyIssue = new MyIssue("Isbn", "Dr", dateOfIssue, new Date(1L));
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualMyIssue.setDateOfIssue(fromResult);
        actualMyIssue.setIsbn("Isbn909090");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualMyIssue.setReturnDate(fromResult1);
        actualMyIssue.setTitle("Doctor's Book");
        assertSame(fromResult, actualMyIssue.getDateOfIssue());
        assertEquals("Isbn909090", actualMyIssue.getIsbn());
        assertSame(fromResult1, actualMyIssue.getReturnDate());
        assertEquals("Doctor's Book", actualMyIssue.getTitle());
    }
}

