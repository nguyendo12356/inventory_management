package com.java.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.Category;

@Repository
@Transactional
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao  {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Category findByName(String name) {
		@SuppressWarnings("unchecked")
		Query<Category> query = sessionFactory.getCurrentSession().createQuery("from Category where name=:name");
		query.setParameter("name", name);
		return query.uniqueResult();
	}
	@Override
	public Category findCategoryById(int id) {
		@SuppressWarnings("unchecked")
		Query<Category> query = sessionFactory.getCurrentSession().createQuery("from Category where id=:id");
		query.setParameter("id", id);
		return query.uniqueResult();
	}

}
