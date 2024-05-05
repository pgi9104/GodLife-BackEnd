package com.gen.script.api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gen.script.api.auth.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUserId(String id);
}
