package com.java.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDaoImpl<E> implements BaseDao<E> {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		StringBuilder query = new StringBuilder("");
		try {
			query.append(" from ").append(getClassName()).append(" as model where model.active = 1");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return sessionFactory.openSession().createQuery(query.toString()).list();
	}

	@Override
	public E findById(Class<E> e, Serializable id) {
		return sessionFactory.getCurrentSession().get(e, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByProperty(String property, Object value) {
		StringBuilder queryString = new StringBuilder();
		try {
			queryString.append(" from ").append(getClassName()).append(" as model where model.active = 1 and model.")
					.append(property).append("=?");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter(0, value);
		return query.getResultList();
	}

	@Override
	public void save(E instance) {
		sessionFactory.getCurrentSession().save(instance);
	}

	@Override
	public void update(E instance) {
		sessionFactory.getCurrentSession().merge(instance);
	}

	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("\\<(>*?)//>");
		Matcher m = pattern.matcher(s);
		String generic = null;
		if (m.find()) {
			generic = m.group(1);
		}
		return generic;
	}

	public String getClassName() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String s = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();
		return s.substring(s.lastIndexOf(".")).substring(1);
	}

	@Override
	public void delete(Class<E> e, int id) {
		sessionFactory.getCurrentSession().delete(findById(e, id));		
	}

	@Override
	public int countRecord() {
		String sql;
		try {
			sql = "Select count(*) from "+getClassName()+" as model where model.active = 1";
			return ((Long)sessionFactory.getCurrentSession().createQuery(sql).uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}
