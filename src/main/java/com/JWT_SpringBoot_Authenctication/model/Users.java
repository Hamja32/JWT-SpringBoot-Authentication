package com.JWT_SpringBoot_Authenctication.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.JWT_SpringBoot_Authenctication.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Users implements UserDetails{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(unique=true)
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	public Users(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}
	@Override
	public  String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
	    return true; // Simple rakhne ke liye true return karein
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	@Override
	public boolean isEnabled() {
	    return true;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Users() {
		super();
	}


	

}

