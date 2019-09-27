package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	public void deleteUser(int id) {
		sessionFactory.getCurrentSession().delete(getUserById(id));
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> getUsers() {
		Query<UserDto> query = sessionFactory.getCurrentSession().createQuery("from "+UserDto.class.getName());;
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> checkLogin(String username, String password) {
		Query<UserDto> query = sessionFactory.openSession()
				.createQuery("from "+UserDto.class.getName()+" where username=:username and password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.list();
	}

	@Override
	public UserDto getUserById(int id) {
		return sessionFactory.getCurrentSession().get(UserDto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDto getUserByUsername(String username) {
		Query<UserDto> query = sessionFactory.getCurrentSession()
				.createQuery("from "+UserDto.class.getName()+" where username=:username");
		query.setParameter("username", username);
		
		return query.uniqueResult();
	}

}
