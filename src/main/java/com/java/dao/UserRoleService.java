package com.java.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Role;
import com.java.entity.UserDto;
import com.java.entity.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void addUserRole(UserDto user, int roleId) {
		UserRole userRole = new UserRole();
		userRole.setActive(true);
		userRole.setCreateDate(new Date());
		userRole.setUserDto(user);
		userRole.setRole(new Role(roleId));
		userRoleDao.save(userRole);
	}

}
