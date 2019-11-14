package com.java.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.Role;
import com.java.entity.UserDto;
import com.java.entity.UserRole;
import com.java.model.User;
import com.java.util.ConvertObject;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void addUser(User user, int roleId) {
		UserDto userDto = ConvertObject.convertUserToUserDto(user);
		userDto.setActive(false);
		userDto.setCreateDate(new Date());
		UserRole userRole = new UserRole();
		userRole.setRole(new Role(roleId));
		userDao.addUser(userDto, userRole);
	}

	@Override
	public void updateUser(User user) {
		UserDto userDto = ConvertObject.convertUserToUserDto(user);
		userDto.setCreateDate(new Date());
		userDao.update(userDto);
	}

	@Override
	public void deleteUser(int idUser) {
		userDao.delete(UserDto.class, idUser);	
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
		List<User> users = new ArrayList<User>();
		userDao.getUsers().forEach( userDto -> users.add(ConvertObject.convertUserDtoToUser(userDto)));
		return users;
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

	@Override
	public void changeStateAccount(int id, boolean active) {
		userDao.changeStateAccount(id, active);	
	}

	@Override
	public User findById(int id) {
		UserDto user = userDao.findById(UserDto.class, id);
		if (user != null) {
			return ConvertObject.convertUserDtoToUser(user);
		}
		return null;
	}

	@Override
	public void updatePassword(String username, String password) {
		UserDto user = userDao.getUserByUsername(username);
		user.setPassword(password);
		userDao.update(user);
	}

	@Override
	public int countNotification() {
		return  notificationService.countNotification();
	}
}
