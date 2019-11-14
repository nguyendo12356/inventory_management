package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.Notification;

@Repository
public class NotificationDaoImpl extends BaseDaoImpl<Notification> implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotification() {
		String sql = "from Notification order by id desc";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public long countNotification() {
		String sql = "select count(*) from Notification where status = 0";
		return (long)sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public void updateStatus() {
		String sql = "update Notification set status = 1";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}
	
}
