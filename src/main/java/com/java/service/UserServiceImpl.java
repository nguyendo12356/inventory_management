package com.java.service;

import java.util.Date;
import java.util.LinkedList;
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
		UserDto userDto = userDao.getUserById(id);
		User user = null;
		if(userDto != null) {
			user = new User();
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setImage(userDto.getImage());
			user.setGender(userDto.isGender());
			user.setActive(userDto.isActive());
			user.setCreateDate(userDto.getCreateDate());
		}
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new LinkedList<User>();
		for (int i = 0 ; i < users.size(); i++) {
			System.out.println(users.get(i).toString());
			User user = new User();
			user.setUsername(users.get(i).getUsername());
			user.setPassword(users.get(i).getPassword());
			user.setEmail(users.get(i).getEmail());
			user.setName(users.get(i).getName());
			user.setGender(users.get(i).isGender());
			user.setImage(users.get(i).getImage());
			user.setActive(true);
			user.setCreateDate(new Date());
			users.add(user);
		}
		return users;
	}

	@Override
	public int checkLogin(String username, String password) {
		return userDao.checkLogin(username, password).size();
	}

	@Override
	public User getUserByUsername(String username) {
		UserDto optional = userDao.getUserByUsername(username);
		User user = null;
		if(optional != null) {
			user = new User();
			user.setUsername(optional.getUsername());
			user.setPassword(optional.getPassword());
			user.setName(optional.getName());
			user.setEmail(optional.getEmail());
			user.setImage(optional.getImage());
			user.setGender(optional.isGender());
			user.setActive(optional.isActive());
			user.setCreateDate(optional.getCreateDate());
		}
		return user;
	}

}
