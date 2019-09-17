package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.User;

@RestController
public class UserControllerApi {

	List<User> list = new ArrayList<User>();


	@GetMapping(value = "/users")
	@ResponseBody
	public ResponseEntity<List<User>> listUser() {
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/users")
	@ResponseBody
	public ResponseEntity<User> addUser(@RequestBody User user) {
		//list.add(new User("user4", "123", "Do"));
		list.add(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String userName) {
		for (int i = 0; i< list.size(); i++) {
//			if (list.get(i).getUserName().equals(userName)) {
//				list.remove(i);
//			}
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}
