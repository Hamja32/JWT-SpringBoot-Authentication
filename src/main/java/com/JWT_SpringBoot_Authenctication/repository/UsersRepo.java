package com.JWT_SpringBoot_Authenctication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JWT_SpringBoot_Authenctication.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

	Optional<Users> findByUsername(String username);
	
}
