package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.model.User;
import com.java.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/signupForm")
	private String loadSignupForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping(value = "/addUser")
	private String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "signup";
	}
	

	
}
