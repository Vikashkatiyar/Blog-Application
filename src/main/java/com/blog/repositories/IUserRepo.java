package com.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

public interface IUserRepo extends JpaRepository<User, Integer> {
     
	Optional<User> findByEmail(String email);
	
}
