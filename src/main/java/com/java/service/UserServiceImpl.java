package com.java.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.UserDto;
import com.java.model.User;
import com.java.util.ConvertObject;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		UserDto userDto = ConvertObject.convertUserToUserDto(user);
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
		UserDto userDto = userDao.getUserById(id);
		if(userDto != null) {
			User user = ConvertObject.convertUserDtoToUser(userDto);
			return user;
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public User checkLogin(String username, String password) {
		List<UserDto> userDtos =  userDao.checkLogin(username, password);
		if (!userDtos.isEmpty()) {
			User user = ConvertObject.convertUserDtoToUser(userDtos.get(0));
			return user;
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		UserDto userDto = userDao.getUserByUsername(username);
		User user = null;
		if(userDto != null) {
			user = ConvertObject.convertUserDtoToUser(userDto);
			return user;
		}
		return user;
	}

}
