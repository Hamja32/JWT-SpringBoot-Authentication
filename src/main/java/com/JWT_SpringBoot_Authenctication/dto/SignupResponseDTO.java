package com.JWT_SpringBoot_Authenctication.dto;
public class SignupResponseDTO {
 private Long id;
 private String username;
 
 
public SignupResponseDTO(Long id, String username) {
	super();
	this.id = id;
	this.username = username;
}
 
}