package com.JWT_SpringBoot_Authenctication.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
	private String username;
	private String password;
//	private Role role;
	
	public LoginRequestDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		
	}
	
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

	
}

