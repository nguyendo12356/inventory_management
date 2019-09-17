package com.java.dao;

import java.util.List;

import com.java.entity.UserDto;

public interface UserDao {

	void addUser(UserDto user);

	void updateUser(UserDto user);

	void deleteUser(int idUser);

	UserDto getUserById(int id);

	List<UserDto> getUsers();
}
