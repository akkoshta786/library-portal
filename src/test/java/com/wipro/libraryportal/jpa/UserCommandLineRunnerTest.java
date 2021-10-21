package com.wipro.libraryportal.jpa;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.entity.User;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserCommandLineRunner.class})
@ExtendWith(SpringExtension.class)
class UserCommandLineRunnerTest {
    @Autowired
    private UserCommandLineRunner userCommandLineRunner;

    @MockBean
    private UserDao userDao;

    @Test
    void testRun() {


        User user = new User();
        user.setMemberId(123L);
        user.setEmail("cicd@cicd.com");
        user.setPassword("Hello123");
        user.setIssues(new HashSet<Issue>());
        user.setAdmin(true);
        when(this.userDao.save((User) any())).thenReturn(user);
        this.userCommandLineRunner.run("Args");
    }
}

