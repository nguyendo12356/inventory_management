package com.java.util;

import com.java.entity.UserDto;
import com.java.model.User;

public class ConvertObject {
	
	public static UserDto convertUserToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setGender(user.isGender());
		userDto.setImage(user.getImage().getOriginalFilename());
		userDto.setActive(user.isActive());
		userDto.setCreateDate(user.getCreateDate());
		return userDto;
	}
	
	public static User convertUserDtoToUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setGender(userDto.isGender());
		user.setImageName(userDto.getImage());
		user.setActive(userDto.isActive());
		user.setCreateDate(userDto.getCreateDate());
		return user;
	}
	
}
