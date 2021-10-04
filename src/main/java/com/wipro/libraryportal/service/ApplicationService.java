package com.wipro.libraryportal.service;

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
		dao.save(user);
		return true;
	}
	
	public String createHash(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt());
	}
}
