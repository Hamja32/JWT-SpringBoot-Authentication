package com.JWT_SpringBoot_Authenctication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.JWT_SpringBoot_Authenctication.enums.Role;
import com.JWT_SpringBoot_Authenctication.dto.LoginRequestDTO;
import com.JWT_SpringBoot_Authenctication.dto.LoginResponseDTO;
import com.JWT_SpringBoot_Authenctication.dto.SignupResponseDTO;
//import com.JWT_SpringBoot_Authenctication.model.Role;
import com.JWT_SpringBoot_Authenctication.model.Users;
import com.JWT_SpringBoot_Authenctication.repository.UsersRepo;
import com.JWT_SpringBoot_Authenctication.security.AuthUtil;


@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  // inject karo
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private UsersRepo userRepo;
	
	public LoginResponseDTO  login(LoginRequestDTO loginRequestDTO) {
		
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
		);
		
		Users user = (Users)auth.getPrincipal();
		String token = authUtil.generateAccessToken(user);

		return new LoginResponseDTO(token,user.getId());	
	}

	public SignupResponseDTO signup(LoginRequestDTO signupRequestDTO) {
		Users user = userRepo.findByUsername(signupRequestDTO.getUsername()).orElse(null);
		if(user != null) throw new IllegalArgumentException("User already exists");
		
		user = userRepo.save(new Users(
		    signupRequestDTO.getUsername(),
		    passwordEncoder.encode(signupRequestDTO.getPassword()),
		    Role.USER
		    
		));
		 return new SignupResponseDTO(user.getId(), user.getUsername());
	}

}
