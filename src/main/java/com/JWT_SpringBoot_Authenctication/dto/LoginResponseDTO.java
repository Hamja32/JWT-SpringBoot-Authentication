package com.JWT_SpringBoot_Authenctication.dto;

public class LoginResponseDTO {	
	private String jwt;
	private Long userID;
	
	
	 public LoginResponseDTO(String jwt, Long id) {
	        this.jwt = jwt;
	        this.userID = id;
	    }

	    // Manually getters
	    public String getJwt() { return jwt; }
	    public Long getUserID() { return userID; }
}
