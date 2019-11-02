package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.IOInventory;
import com.java.model.InventoryModel;

public interface IODao extends BaseDao<IOInventory> {
	
	List<IOInventory> getAll();
	
	void saveInvoice(InventoryModel model);
	
	IOInventory findByCodeBill(String codeBill);

}
