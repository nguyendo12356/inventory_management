package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/signup"})
	public String signup() {
		return "signup";
	}
	
	@GetMapping(value = {"/home"})
	public String home() {
		return "home";
	}

}
