package com.wipro.libraryportal.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.User;

@Component
public class UserCommandLineRunner implements CommandLineRunner {


    @Autowired
    private UserDao dao;
    
    private static final String DEFAULT_PASSWORD = "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme";

    @Override
    public void run(String... args) {
//         save a couple of users
        dao.save(new User("atul.koshta@wipro.com", DEFAULT_PASSWORD, true));
        dao.save(new User("nachiketa.kumar@wipro.com", DEFAULT_PASSWORD));
        dao.save(new User("chilla.reddy2@wipro.com", DEFAULT_PASSWORD, true));
        dao.save(new User("akshay.koshta@gmail.com", DEFAULT_PASSWORD));

    }

}

