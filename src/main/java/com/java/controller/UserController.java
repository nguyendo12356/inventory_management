package com.java.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
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
	private String addUser(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request) throws IOException {
		if(!Util.validateEmail(user.getEmail())) {
			model.addAttribute("errorEmail", env.getProperty("error.email"));
		}else if (!Util.validatePassword(user.getPassword())) {
			model.addAttribute("errorPassword", env.getProperty("error.password"));
		}else if (!Util.saveImage( new File(request.getServletContext().getRealPath("static\\images")), user.getImage())) {
			model.addAttribute("errorImage", env.getProperty("error.image.extension"));
		}else if(user.getImage().getSize() > 600*1024){
			model.addAttribute("errorImage", env.getProperty("error.image.size"));
		}else if (userService.getUserByUsername(user.getUsername()) == null) {
			model.addAttribute("success",env.getProperty("signup.success"));
			model.addAttribute("user", new User());
			userService.addUser(user);
		}else {
			model.addAttribute("error",env.getProperty("error.username"));
		}
		return "signup";
	}
	
	@GetMapping(value = {"/loginForm"})
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
		}else if (!user1.isActive()) {
			System.out.println(user1.toString());
			model.addAttribute("error",env.getProperty("error.active"));
			return "login";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("session", user1);
			Hibernate.initialize(user1.getRole());
			return "redirect:/home";
		}
	}
	
	@GetMapping(value = "/logout")
	private String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		request.setAttribute("user", new User());
		return "redirect:/loginForm";
	}

}
