package com.wipro.libraryportal.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.User;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserDao dao;

    @Override
    public void run(String... args) {
//         save a couple of users
        dao.save(new User("atul.koshta@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme", true));
        dao.save(new User("nachiketa.kumar@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme"));
        
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : dao.findAll()) {
            log.info(user.toString());
        }

    }

}

