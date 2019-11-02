package com.java.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.IOInventory;
import com.java.model.InventoryModel;

@Repository
@Transactional
public class IODaoImpl extends BaseDaoImpl<IOInventory> implements IODao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<IOInventory> getAll() {
		@SuppressWarnings("unchecked")
		Query<IOInventory> query = sessionFactory.getCurrentSession().createQuery("from IOInventory where type = 1");
		return query.list();
	}

	@Override
	public void saveInvoice(InventoryModel model) {
		Session s = sessionFactory.openSession();
		try {
			s.save(model);
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
	
}
