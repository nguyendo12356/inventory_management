package com.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.java.util.Util;

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
		if (Util.validate(user.getPassword(), user.getEmail()).equals("")) {
			if (userService.getUserByUsername(user.getUsername()) == null) {
				model.addAttribute("success",env.getProperty("signup.success"));
				model.addAttribute("user", new User());
				userService.addUser(user);
			}else {
				model.addAttribute("error",env.getProperty("error.username"));
			}
		}else {
			model.addAttribute("errorEmailOrPassword",Util.validate(user.getPassword(), user.getEmail()));
		}
		return "signup";
	}
	
	@GetMapping(value = {"/loginForm","/"})
	private String loadLoginForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping(value = "/login")
	private String login(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request) {
		User user1 = userService.checkLogin(user.getUsername(), user.getPassword());
		if (user1 == null) {
			model.addAttribute("error",env.getProperty("error.login"));
			return "login";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user1);
			return "redirect:home";
		}
	}

}
