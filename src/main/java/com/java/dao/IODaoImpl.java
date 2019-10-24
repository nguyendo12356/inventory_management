package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.IOInventory;

@Repository
public class IODaoImpl extends BaseDaoImpl<IOInventory> implements IODao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<IOInventory> getAll() {
		@SuppressWarnings("unchecked")
		Query<IOInventory> query = sessionFactory.getCurrentSession().createQuery("from IOInventory where type = 1");
		return query.list();
	}
	
}
