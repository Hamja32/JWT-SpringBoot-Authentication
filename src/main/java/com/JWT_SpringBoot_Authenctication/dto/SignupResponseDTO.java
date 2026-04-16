package com.JWT_SpringBoot_Authenctication.dto;
public class SignupResponseDTO {
 private Long id;
 private String username;
 
 
public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public SignupResponseDTO(Long id, String username) {
	super();
	this.id = id;
	this.username = username;
}
 
}