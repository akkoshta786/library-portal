package com.wipro.libraryportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UserBookDtoTest {
    @Test
    void testConstructor() {
        Date dateOfIssue = new Date(1L);
        UserBookDto actualUserBookDto = new UserBookDto(123L, "book@example.org", "Isbn", "Coding Book", dateOfIssue,
                new Date(1L));
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualUserBookDto.setDateOfIssue(fromResult);
        actualUserBookDto.setEmail("book@example.org");
        actualUserBookDto.setIsbn("Isbn");
        actualUserBookDto.setIssueId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualUserBookDto.setReturnDate(fromResult1);
        actualUserBookDto.setTitle("Coding Book");
        assertSame(fromResult, actualUserBookDto.getDateOfIssue());
        assertEquals("book@example.org", actualUserBookDto.getEmail());
        assertEquals("Isbn", actualUserBookDto.getIsbn());
        assertEquals(123L, actualUserBookDto.getIssueId());
        assertSame(fromResult1, actualUserBookDto.getReturnDate());
        assertEquals("Coding Book", actualUserBookDto.getTitle());
    }

    @Test
    void testConstructor2() {
        Date date = new Date(1L);
        Date date1 = new Date(1L);
        UserBookDto actualUserBookDto = new UserBookDto(123L, "book@example.org", "Isbn", "Coding Book", date, date1);

        assertSame(date, actualUserBookDto.getDateOfIssue());
        assertEquals("Coding Book", actualUserBookDto.getTitle());
        assertSame(date1, actualUserBookDto.getReturnDate());
        assertEquals(123L, actualUserBookDto.getIssueId());
        assertEquals("Isbn", actualUserBookDto.getIsbn());
        assertEquals("book@example.org", actualUserBookDto.getEmail());
    }
}

