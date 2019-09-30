package com.java.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.User;
import com.java.service.UserService;

@RestController
public class UserControllerApi {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/user/changeState/{id}/{active}")
	private ResponseEntity<User> changeStateAccount(@PathVariable("id") int id, @PathVariable("active") int active) {
		userService.changeStateAccount(id, active);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
}
