package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.UserDto;
import com.java.entity.UserRole;

public interface UserDao extends BaseDao<UserDto> {

	void addUser(UserDto user, UserRole userRole);

	List<UserDto> checkLogin(String username, String password);
	
	UserDto getUserById(int id);

	List<UserDto> getUsers();
	
	UserDto getUserByUsername(String username);
	
	void changeStateAccount(int id,boolean active);
	
}
