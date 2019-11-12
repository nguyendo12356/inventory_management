package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.IOInventory;

public interface IODao extends BaseDao<IOInventory> {
	
	List<IOInventory> getAll(int type);
		
	IOInventory findByCodeBill(String codeBill);
	
	IOInventory findIOInventoryById(int id, int type);
	
	List<IOInventory> getRevenue();

	double getTotalRevenue();
}
