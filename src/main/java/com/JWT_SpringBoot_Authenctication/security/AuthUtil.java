package com.JWT_SpringBoot_Authenctication.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.JWT_SpringBoot_Authenctication.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtil {

	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public  String generateAccessToken(Users user) {
		return Jwts.builder()
				.subject(user.getUsername().toString())
				.claim("userID",user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+ 1000 * 60*10))
				.signWith(getSecretKey())
				.compact();			
	}
}
