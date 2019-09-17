package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.entity.UserDto;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(UserDto user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(UserDto user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void deleteUser(int idUser) {
		sessionFactory.getCurrentSession().delete(getUserById(idUser));
	}

	@Override
	public UserDto getUserById(int id) {
		return null;
	}

	@Override
	public List<UserDto> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
