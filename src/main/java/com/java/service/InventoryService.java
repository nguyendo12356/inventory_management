package com.java.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.IODao;
import com.java.dao.ProductDao;
import com.java.entity.IOInventory;
import com.java.entity.Product;
import com.java.model.InventoryModel;
import com.java.model.ProductModel;

@Service
@Transactional
public class InventoryService {

	@Autowired
	private IODao ioDao;

	@Autowired
	private ProductDao productDao;

	public void addInvoice(InventoryModel inventoryModel) {
		Product p;
		IOInventory invoice = new IOInventory(inventoryModel.getCodeBill(), 1,
				inventoryModel.getSuplier(), inventoryModel.getTotalPrice(), inventoryModel.getStaffName());
		for( ProductModel product : inventoryModel.getProducts()) {
			p = productDao.findById(Product.class, product.getId());
			System.out.println(p.toString());
			productDao.update(p);
		}
		ioDao.save(invoice);
	}
}
