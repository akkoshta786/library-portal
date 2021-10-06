package com.wipro.libraryportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.User;
import com.wipro.libraryportal.jpa.UserDao;

@Component
public class ApplicationService {
	
	@Autowired
	private UserDao dao;
	
	public boolean registerUser(User user) {
		if(dao.existsById(user.getEmail())) {
			return false;
		}
		dao.save(user);
		return true;
	}
	
	public boolean isValidUser(String email, String pwd) {
		Optional<User> user =  dao.findById(email);
		if(user.isPresent()) {
			if(BCrypt.checkpw(pwd, user.get().getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public String getHash(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt());
	}
	
}
