package com.java.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.Menu;

@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao<Menu> {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void deleteMenu(int id) {
		Menu menu = findById(Menu.class, id);
		sessionFactory.getCurrentSession().delete(menu);
	}
	
	
}
