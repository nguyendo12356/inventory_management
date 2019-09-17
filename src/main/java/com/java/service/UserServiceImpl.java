package com.java.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.UserDto;
import com.java.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setGender(user.isGender());
		userDto.setImage(user.getImage());
		userDto.setActive(true);
		userDto.setCreateDate(new Date());
		userDao.addUser(userDto);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
