package com.wipro.libraryportal.jpa;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.wipro.libraryportal.entity.Book;
import com.wipro.libraryportal.entity.Issue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookCommandLineRunner.class})
@ExtendWith(SpringExtension.class)
class BookCommandLineRunnerTest {
    @Autowired
    private BookCommandLineRunner bookCommandLineRunner;

    @MockBean
    private BookDao bookDao;

    @Test
    void testRun()
    {
        Book book = new Book();
        book.setPublisher("Publisher");
        book.setIssues(new HashSet<Issue>());
        book.setLanguage("English");
        book.setIsbn("Isbn2021");
        book.setTitle("Everyone has a Story");
        book.setAuthor("Savi Sharma");
        book.setCopies(1);
        book.setNumberOfPages(10);
        when(this.bookDao.save((Book) any())).thenReturn(book);
        this.bookCommandLineRunner.run("Args");
    }
}

