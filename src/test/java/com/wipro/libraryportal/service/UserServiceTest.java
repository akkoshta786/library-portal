package com.wipro.libraryportal.service;

import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.jpa.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@Transactional
@SpringBootTest
class UserServiceTest {


	@InjectMocks
	private UserService userService;

	@Mock
	private UserDao userDao;

	List<User> users;

	@Test
	void testGetUserByEmail() {
		when(this.userDao.findByEmail((String) any())).thenReturn(new ArrayList<User>());
		assertNull(this.userService.getUserByEmail("new@cicd.org"));
		verify(this.userDao).findByEmail((String) any());
		assertTrue(this.userService.users.isEmpty());
	}

	@Test
	void testGetUserByEmail2() {
		User user = new User();
		user.setMemberId(123L);
		user.setEmail("new@cicd.com");
		user.setPassword("password");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		user.setDob(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		user.setIssues(new HashSet<Issue>());
		user.setAdmin(true);

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user);
		when(this.userDao.findByEmail((String) any())).thenReturn(userList);
		assertSame(user, this.userService.getUserByEmail("new@cicd.org"));
		verify(this.userDao).findByEmail((String) any());
		assertEquals(1, this.userService.users.size());
	}

	@BeforeEach
	public void setUp() {
		users = new ArrayList<>();

	}

	@Test
	void isAdminTrueTest()
	{
		users.add(new User("atul.koshta@wipro.com", "pass", new Date(), true));
		when(userDao.findByEmail("atul.koshta@wipro.com")).thenReturn(users);
		assertTrue(userService.isAdmin("atul.koshta@wipro.com"));
	}

	@Test
	void isAdminFalseTest() {
		users.add(new User("nachiketa.kumar@wipro.com", "pass1", new Date()));
		when(userDao.findByEmail("nachiketa.kumar@wipro.com")).thenReturn(users);
		assertFalse(userService.isAdmin("nachiketa.kumar@wipro.com"));

	}

	@Test
	void isValidUserTest() {
		users.add(new User("nachiketa.kumar@wipro.com", "$2a$10$N4HmiNQfuBWu1twBWQ/fkeR2GLvsXUNotBpyzKYHnIs/00Wdguyme", new Date()));
		when(userDao.findByEmail("nachiketa.kumar@wipro.com")).thenReturn(users);
		assertTrue(userService.isValidUser("nachiketa.kumar@wipro.com", "pass"));
	}

	@Test
	void registerUserTest() {
		when(userDao.findByEmail("dummy.user@wipro.com")).thenReturn(users);
		assertTrue(userService.registerUser(new User("dummy.user@wipro.com", "passwordhashgoeshere", new Date())));
	}

	@Test
	void testUpdatePassword() {
		doNothing().when(this.userDao).updatePassword((String) any(), (String) any());
		assertTrue(this.userService.updatePassword("", "nachiketa.kumar@wipro.com"));
		verify(this.userDao).updatePassword((String) any(), (String) any());
	}

}
