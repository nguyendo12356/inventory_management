package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.IODao;
import com.java.entity.IOInventory;

@Service
public class IOService {
	
	@Autowired
	private IODao ioDao;
	
	public List<IOInventory> getAll(int type){
		return ioDao.getAll(type);
	}
	
	public IOInventory findIOInventoryById(int id, int type) {
		return ioDao.findIOInventoryById(id,type);
	}
	
	public List<IOInventory> getRevenue(){
		return ioDao.getRevenue();
	}
	
	public double getTotalRevenue() {
		return ioDao.getTotalRevenue();
	}

}
