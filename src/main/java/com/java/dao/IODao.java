package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.IOInventory;

public interface IODao extends BaseDao<IOInventory> {
	
	List<IOInventory> getAll();

}
