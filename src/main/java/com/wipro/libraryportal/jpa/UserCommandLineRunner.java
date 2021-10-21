package com.wipro.libraryportal.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.User;

@Component
public class UserCommandLineRunner implements CommandLineRunner {


    @Autowired
    private UserDao dao;
    
   
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    private static final String DEFAULT_PASSWORD = "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme";

    @Override
    public void run(String... args) throws ParseException{
//         save a couple of users
        dao.save(new User("atul.koshta@wipro.com", DEFAULT_PASSWORD, formatter.parse("10/29/1997"), true));
        dao.save(new User("nachiketa.kumar@wipro.com", DEFAULT_PASSWORD, formatter.parse("09/20/1996")));
        dao.save(new User("chilla.reddy2@wipro.com", DEFAULT_PASSWORD, formatter.parse("12/02/1994"), true));
        dao.save(new User("akshay.koshta@gmail.com", DEFAULT_PASSWORD, formatter.parse("07/27/2000")));

    }

}

