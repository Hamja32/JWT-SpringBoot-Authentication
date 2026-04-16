package com.JWT_SpringBoot_Authenctication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.JWT_SpringBoot_Authenctication.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(c -> c.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/public/**", "/auth/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
				).addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
