package com.wipro.libraryportal.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.libraryportal.dto.MyIssue;
import com.wipro.libraryportal.dto.UserBookDto;
import com.wipro.libraryportal.entity.Book;
import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.jpa.IssueDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {IssueService.class})
@ExtendWith(SpringExtension.class)
class IssueServiceTest {
    @MockBean
    private IssueDao issueDao;

    @Autowired
    private IssueService issueService;

    @Test
    void testSaveIssue() {
        Book book = new Book();
        book.setPublisher("PublisherRandom1");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("english");
        book.setIsbn("98471025354");
        book.setTitle("Mybook");
        book.setAuthor("Nachiketa");
        book.setCopies(1);
        book.setNumberOfPages(10);

        User user = new User();
        user.setMemberId(123L);
        user.setEmail("cicd@cicd.com");
        user.setPassword("Hello123");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);

        Issue issue = new Issue();
        issue.setIsbn("Isbn");
        issue.setIssueId(123L);
        issue.setBook(book);
        issue.setStatus(1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setDateOfIssue(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        issue.setUser(user);
        issue.setMemberId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setReturnDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.issueDao.save((Issue) any())).thenReturn(issue);

        Book book1 = new Book();
        book1.setPublisher("Publisher");
        book1.setIssues(new HashSet<Issue>());
        book1.setLanguage("english");
        book1.setIsbn("Isbn");
        book1.setTitle("Rain");
        book1.setAuthor("Mary");
        book1.setCopies(1);
        book1.setNumberOfPages(10);

        User user1 = new User();
        user1.setMemberId(123L);
        user1.setEmail("cicd@cicd.com");
        user1.setPassword("Hello");
        user1.setIssues(new HashSet<Issue>());
        user1.setAdmin(true);

        Issue issue1 = new Issue();
        issue1.setIsbn("Isbn");
        issue1.setIssueId(123L);
        issue1.setBook(book1);
        issue1.setStatus(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue1.setDateOfIssue(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        issue1.setUser(user1);
        issue1.setMemberId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue1.setReturnDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        assertTrue(this.issueService.saveIssue(issue1));
        verify(this.issueDao).save((Issue) any());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testFindIssueById() {
        Book book = new Book();
        book.setPublisher("Publisher1");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("english");
        book.setIsbn("Isbn");
        book.setTitle("Heads you win");
        book.setAuthor("Jeffery Archer");
        book.setCopies(1);
        book.setNumberOfPages(10);

        User user = new User();
        user.setMemberId(123L);
        user.setEmail("jef@example.com");
        user.setPassword("1234");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);

        Issue issue = new Issue();
        issue.setIsbn("Isbn");
        issue.setIssueId(123L);
        issue.setBook(book);
        issue.setStatus(1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setDateOfIssue(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        issue.setUser(user);
        issue.setMemberId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setReturnDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Issue> ofResult = Optional.<Issue>of(issue);
        when(this.issueDao.findByIssueId(anyLong())).thenReturn(ofResult);
        Optional<Issue> actualFindIssueByIdResult = this.issueService.findIssueById(123L);
        assertSame(ofResult, actualFindIssueByIdResult);
        assertTrue(actualFindIssueByIdResult.isPresent());
        verify(this.issueDao).findByIssueId(anyLong());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testGetAllIssues() {
        ArrayList<UserBookDto> userBookDtoList = new ArrayList<UserBookDto>();
        when(this.issueDao.fetchAllIssuesJoin()).thenReturn(userBookDtoList);
        List<UserBookDto> actualAllIssues = this.issueService.getAllIssues();
        assertSame(userBookDtoList, actualAllIssues);
        assertTrue(actualAllIssues.isEmpty());
        verify(this.issueDao).fetchAllIssuesJoin();
    }

    @Test
    void testCheckBookAvailibilityWithMember() {
        when(this.issueDao.findIssueByEmailAndIsbn(anyLong(), (String) any())).thenReturn(new ArrayList<Issue>());
        assertFalse(this.issueService.checkBookAvailibilityWithMember(123L, "Isbn"));
        verify(this.issueDao).findIssueByEmailAndIsbn(anyLong(), (String) any());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testCheckBookAvailibilityWithMember2() {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("french and english");
        book.setIsbn("Isbn");
        book.setTitle("Lord of the rings");
        book.setAuthor("R.R.Tolkien");
        book.setCopies(0);
        book.setNumberOfPages(10);

        User user = new User();
        user.setMemberId(123L);
        user.setEmail("tolkien@example.org");
        user.setPassword("123");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);

        Issue issue = new Issue();
        issue.setIsbn("Isbn");
        issue.setIssueId(123L);
        issue.setBook(book);
        issue.setStatus(0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setDateOfIssue(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        issue.setUser(user);
        issue.setMemberId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setReturnDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Issue> issueList = new ArrayList<Issue>();
        issueList.add(issue);
        when(this.issueDao.findIssueByEmailAndIsbn(anyLong(), (String) any())).thenReturn(issueList);
        assertTrue(this.issueService.checkBookAvailibilityWithMember(123L, "Isbn"));
        verify(this.issueDao).findIssueByEmailAndIsbn(anyLong(), (String) any());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testGetMyIssues() {
        ArrayList<MyIssue> myIssueList = new ArrayList<MyIssue>();
        when(this.issueDao.findMyIssues(anyLong())).thenReturn(myIssueList);
        List<MyIssue> actualMyIssues = this.issueService.getMyIssues(123L);
        assertSame(myIssueList, actualMyIssues);
        assertTrue(actualMyIssues.isEmpty());
        verify(this.issueDao).findMyIssues(anyLong());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testUpdateIssueStatus() {
        doNothing().when(this.issueDao).updateIssueStatus(anyLong());
        this.issueService.updateIssueStatus(123L);
        verify(this.issueDao).updateIssueStatus(anyLong());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testCurrentlyIssued() {
        when(this.issueDao.findAllIssuesByIsbn((String) any())).thenReturn(new ArrayList<Issue>());
        assertFalse(this.issueService.currentlyIssued("Isbn"));
        verify(this.issueDao).findAllIssuesByIsbn((String) any());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }

    @Test
    void testCurrentlyIssued2() {
        Book book = new Book();
        book.setPublisher("Self Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("English");
        book.setIsbn("Isbn2021");
        book.setTitle("Everyone has a story");
        book.setAuthor("Savi Sharma");
        book.setCopies(0);
        book.setNumberOfPages(10);

        User user = new User();
        user.setMemberId(123L);
        user.setEmail("cicd@cicd.com");
        user.setPassword("Hello?123");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);

        Issue issue = new Issue();
        issue.setIsbn("Isbn2001");
        issue.setIssueId(123L);
        issue.setBook(book);
        issue.setStatus(0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setDateOfIssue(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        issue.setUser(user);
        issue.setMemberId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        issue.setReturnDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Issue> issueList = new ArrayList<Issue>();
        issueList.add(issue);
        when(this.issueDao.findAllIssuesByIsbn((String) any())).thenReturn(issueList);
        assertTrue(this.issueService.currentlyIssued("Isbn"));
        verify(this.issueDao).findAllIssuesByIsbn((String) any());
        assertTrue(this.issueService.getAllIssues().isEmpty());
    }
}

