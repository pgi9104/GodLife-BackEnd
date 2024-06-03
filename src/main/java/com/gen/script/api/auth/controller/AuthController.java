package com.gen.script.api.auth.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gen.script.api.auth.entity.User;
import com.gen.script.api.auth.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/userinfo")
	public ResponseEntity<EntityModel<User>> getUser() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = new User();
		user.setUserId(userId);
		
		user = userService.getUser(user);
		
		return ResponseEntity.ok().body(
				EntityModel
					.of(user)
					.add(linkTo(methodOn(AuthController.class).getUser()).withSelfRel().withType("GET"))
		);
	}
	
	@PostMapping("/api/user/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PostMapping("/api/user/auth")
	public void auth(@RequestBody User user) {
	}
}