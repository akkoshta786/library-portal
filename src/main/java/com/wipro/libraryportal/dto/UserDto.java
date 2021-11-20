package com.wipro.libraryportal.dto;


public class UserDto {
	private String email;
	private String password;
	private String dob;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String email, String password, String dob) {
		super();
		this.email = email;
		this.password = password;
		this.dob = dob;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
}
