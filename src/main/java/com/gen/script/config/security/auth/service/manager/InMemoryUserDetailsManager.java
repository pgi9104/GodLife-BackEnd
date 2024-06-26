package com.gen.script.config.security.auth.service.manager;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsManager implements UserDetailsService{
	private final List<UserDetails> users;
	
	public InMemoryUserDetailsManager(List<UserDetails> users) {
		this.users = users;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.stream()
			.filter(
				u -> u.getUsername().equals(username)
			)
			.findFirst()
			.orElseThrow(
				() -> new UsernameNotFoundException("User not found")	
			);
	}
}
