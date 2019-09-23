package com.java.dao;

import java.util.List;

import com.java.entity.UserDto;

public interface UserDao {

	void addUser(UserDto user);

	void updateUser(UserDto user);

	void deleteUser(int id);

	List<UserDto> checkLogin(String username, String password);
	
	UserDto getUserById(int id);

	List<UserDto> getUsers();
	
	UserDto getUserByUsername(String username);
	
}
