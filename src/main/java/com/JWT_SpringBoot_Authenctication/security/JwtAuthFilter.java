package com.JWT_SpringBoot_Authenctication.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JWT_SpringBoot_Authenctication.service.CustomUserDetailsService;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	@Autowired
	private AuthUtil authUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
			try {
				username = Jwts.parser()
						.verifyWith(authUtil.getSecretKey())
						.build()
						.parseSignedClaims(jwt)
						.getPayload()
						.getSubject();
			} catch (Exception e) {
				System.out.println("Token expired OR Token is wrong : " + e.getMessage());
			}
		}
		if(username  != null && SecurityContextHolder.getContext() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
		}
		filterChain.doFilter(request, response);
	}
	
	
	
}
