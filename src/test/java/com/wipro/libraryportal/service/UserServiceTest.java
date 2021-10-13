package com.wipro.libraryportal.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.jpa.UserDao;


@RunWith(MockitoJUnitRunner.class)
@Transactional
@SpringBootTest
class UserServiceTest {
	
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserDao userDao;
	
	List<User> users;
	
	@BeforeEach
	public void setUp() {
		users = new ArrayList<>();
		
	}
	
	@Test
	void isAdminTrueTest() {
		users.add(new User("atul.koshta@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme", true));
		when(userDao.findByEmail("atul.koshta@wipro.com")).thenReturn(users);
		assertTrue(userService.isAdmin("atul.koshta@wipro.com"));

	}
	
	@Test
	void isAdminFalseTest() {
		users.add(new User("nachiketa.kumar@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme"));
		when(userDao.findByEmail("nachiketa.kumar@wipro.com")).thenReturn(users);
		assertFalse(userService.isAdmin("nachiketa.kumar@wipro.com"));

	}
	
	@Test
	public void isValidUserTest() {
		users.add(new User("nachiketa.kumar@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme"));
		when(userDao.findByEmail("nachiketa.kumar@wipro.com")).thenReturn(users);
		assertTrue(userService.isValidUser("nachiketa.kumar@wipro.com", "pass"));
	}
	
	@Test
	public void registerUserTest() {
		when(userDao.findByEmail("dummy.user@wipro.com")).thenReturn(users);
		assertTrue(userService.registerUser(new User("dummy.user@wipro.com", "passwordhashgoeshere")));
	}

}
