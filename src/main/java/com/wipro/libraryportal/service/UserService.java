package com.wipro.libraryportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.jpa.UserDao;


@Component
public class UserService {
	@Autowired
	private UserDao dao;
	
	List<User> users;
	
	public User getUserByEmail(String email) {
		users = dao.findByEmail(email);
		if(!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
	
	public boolean registerUser(User newUser) {
		
		users = dao.findByEmail(newUser.getEmail());
		if(!users.isEmpty()) {
			return false;
		}
		dao.save(newUser);
		return true;
	}
	
	public boolean isValidUser(String email, String pwd) {
		users = dao.findByEmail(email);
		return !users.isEmpty() && BCrypt.checkpw(pwd, users.get(0).getPassword());
	
	}
	
	public boolean isAdmin(String email) {
		users = dao.findByEmail(email);
		if(!users.isEmpty()) {
			return users.get(0).isAdmin();
		}
		return false;
	}
	
	public boolean updatePassword(String newPassword, String email) {
		dao.updatePassword(newPassword, email);
		return true;
	}
	
}
