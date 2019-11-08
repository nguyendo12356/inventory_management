package com.java.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.IOInventory;

@Repository
@Transactional
public class IODaoImpl extends BaseDaoImpl<IOInventory> implements IODao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<IOInventory> getAll(int type) {
		@SuppressWarnings("unchecked")
		Query<IOInventory> query = sessionFactory.getCurrentSession().createQuery("from IOInventory where type =: type ");
		query.setParameter("type", type);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public IOInventory findByCodeBill(String codeBill) {
		StringBuilder sb = new StringBuilder("");
		Query<IOInventory> query = null;
		try {
			sb.append("from ").append(getClassName()).append(" as model where model.codeBill=:value");
			query = sessionFactory.getCurrentSession().createQuery(sb.toString());
			query.setParameter("value", codeBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.uniqueResult();
	}

	@Override
	public IOInventory findIOInventoryById(int id, int type) {
		@SuppressWarnings("unchecked")
		Query<IOInventory> query = sessionFactory.openSession().createQuery("from IOInventory where type =: type and id=:id");
		query.setParameter("id", id);
		query.setParameter("type", type);
		return query.uniqueResult();
	}

	@Override
	public List<IOInventory> getRevenue() {
		StringBuilder sb = new StringBuilder();
		Date date = DateUtils.addDays(new Date(), -12);
		sb.append("from IOInventory where createDate between :sDate and :eDate");
		Query<IOInventory> query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter("sDate", date);
		query.setParameter("eDate", new Date());
		return query.list();
	}
	
}
