package com.JWT_SpringBoot_Authenctication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWT_SpringBoot_Authenctication.dto.LoginRequestDTO;
import com.JWT_SpringBoot_Authenctication.dto.LoginResponseDTO;
import com.JWT_SpringBoot_Authenctication.dto.SignupResponseDTO;
import com.JWT_SpringBoot_Authenctication.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	public  AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
		return ResponseEntity.ok(authService.login(loginRequestDTO));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginRequestDTO signupRequestDTO){
		return ResponseEntity.ok(authService.signup(signupRequestDTO));
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "This is ADMIN PAGE";
	}
}
