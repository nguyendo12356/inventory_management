package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.InvoiceProduct;

@Repository
public class InvoiceProductImpl extends BaseDaoImpl<InvoiceProduct> implements InvoiceProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceProduct> getAll() {
		Query<InvoiceProduct> query = sessionFactory.getCurrentSession().createQuery("from InvoiceProduct");
		return query.list();
	}

}
