package com.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Role;
import com.java.entity.UserDto;
import com.java.entity.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void addUserRole(int userId, int roleId) {
		userRoleDao.save(new UserRole(new UserDto(userId), new Role(roleId)));
	}

}
