package com.wipro.libraryportal.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wipro.libraryportal.entity.User;

public interface UserDao extends CrudRepository<User, String> {
	List<User> findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.password = :password WHERE u.email = :email")
	void updatePassword(@Param(value="password") String password, @Param(value="email") String email);
}
