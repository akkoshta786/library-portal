package com.wipro.libraryportal.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wipro.libraryportal.entity.User;

public interface UserDao extends CrudRepository<User, String> {
	List<User> findByEmail(String email);
}
