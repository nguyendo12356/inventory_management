package com.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.common.BaseDaoImpl;
import com.java.entity.UserDto;
import com.java.entity.UserRole;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<UserDto> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(UserDto user, UserRole userRole) {
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.save(user);
			userRole.setUserDto(user);
			s.save(userRole);
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Add User error");
		}finally {
			s.close();			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> getUsers() {
		Session session = sessionFactory.openSession();
		Query<UserDto> query = session.createQuery("from "+UserDto.class.getName());
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
		return sessionFactory.openSession().get(UserDto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDto getUserByUsername(String username) {
		Query<UserDto> query = sessionFactory.getCurrentSession()
				.createQuery("from "+UserDto.class.getName()+" where username=:username");
		query.setParameter("username", username);
		
		return query.uniqueResult();
	}

	@Override
	public void changeStateAccount(int id, boolean active) {
		UserDto user = findById(UserDto.class, id);
		user.setActive(active);
		update(user);
	}

}
