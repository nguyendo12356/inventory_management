package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.model.User;

@Controller
public class HomeController {

	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if(request.getSession().getAttribute("session") == null) {
			request.setAttribute("user", new User());
			return "login";
		}
		return "home";
	}
	
}
