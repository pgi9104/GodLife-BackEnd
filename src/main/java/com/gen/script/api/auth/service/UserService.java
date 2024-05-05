package com.gen.script.api.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gen.script.api.auth.entity.User;
import com.gen.script.api.auth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public User getUser(User user) {
		return userRepository.findByUserId(user.getUserId());
	}
}