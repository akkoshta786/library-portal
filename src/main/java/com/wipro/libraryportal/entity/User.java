package com.wipro.libraryportal.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId;
	
	private String email;
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean admin;
	
	@OneToMany(mappedBy = "user")
	private Set<Issue> issues;
		
	public User() {
		super();
	}

	public User(String email, String password, Date dob) {
		super();
		this.email = email;
		this.password = password;
		this.dob = dob;
	}

	public User(String email, String password, Date dob, boolean admin) {
		super();
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.admin = admin;
	}
	
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [email=" + email + "]";
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	
	
}
