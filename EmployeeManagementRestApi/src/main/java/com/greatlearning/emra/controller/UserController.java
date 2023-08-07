package com.greatlearning.emra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emra.entity.User;
import com.greatlearning.emra.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	// Add user
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);

	}
}
