package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.model.User;
import com.java.service.UserService;

@Controller
@PropertySource("classpath:error.properties")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;

	@GetMapping(value = "/signupForm")
	private String loadSignupForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping(value = "/addUser")
	private String addUser(@ModelAttribute("user") User user, ModelMap model) {
		if (userService.getUserByUsername(user.getUsername()) == null) {
			userService.addUser(user);
		}else {
			model.addAttribute("error",env.getProperty("error.username"));
		}
		return "signup";
	}
	
	@GetMapping(value = {"/loginForm","/"})
	private String loadLoginForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping(value = "/login")
	private String login(@ModelAttribute("user") User user) {
		int count = userService.checkLogin(user.getUsername(), user.getPassword());
		if (count < 1)
			return "login";
		else
			return "redirect:home";
	}	
}
