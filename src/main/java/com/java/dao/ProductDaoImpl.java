package com.java.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product findProductByCode(String code) {
		String sql = "from Product where code =: code";
		@SuppressWarnings("unchecked")
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("code", code);
		return query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> showCodeByLetter(String code) {
		String sql = "select code from Product where code like ?0";
		if(!StringUtils.isBlank(code)) {
			Query<String> query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter(0, code+"%");
			return query.list();
		}else {
			return null;
		}
	}
	
}
