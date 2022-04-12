package com.spdp.bean;

import javax.validation.constraints.NotEmpty;

public class AuthRequest {
	@NotEmpty(message = "Email should not be empty.")
	//@Email(message = "Invalid Email.")
	private String username;
	@NotEmpty(message = "Password should not be empty.")
	//@Size(min = 6, max = 10, message = "Password should be 6 to 10 characters.")
	private String password;
	private int role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	

}
