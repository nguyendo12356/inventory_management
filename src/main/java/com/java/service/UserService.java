package com.java.service;

import java.util.List;

import com.java.model.User;

public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(int idUser);

	User getUserById(int id);

	List<User> getUsers();
	
	int checkLogin(String username, String password);
	
	User getUserByUsername(String username);
}
