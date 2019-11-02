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
import com.java.util.ConvertObject;

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
		ioDao.save(invoice);
		for( ProductModel product : inventoryModel.getProducts()) {
			p = productDao.findProductByCode(product.getCode());
			if(p != null) {
				p.setQuantity(product.getQuantity() + p.getQuantity());
				p.setPrice(product.getPrice());
				p.setDiscount(product.getDiscount());
				productDao.update(p);
			}else {
				p = ConvertObject.parseProduct(product);
				p.setImg_url("noimage.png");
				productDao.save(p);
			}
		}
	}
}
