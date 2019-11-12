package com.java.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.IODao;
import com.java.dao.InvoiceProductDao;
import com.java.dao.ProductDao;
import com.java.entity.IOInventory;
import com.java.entity.InvoiceProduct;
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
	
	@Autowired
	private InvoiceProductDao ipDao;

	public void addInvoice(InventoryModel inventoryModel) {
		Product p;
		IOInventory invoice = new IOInventory(inventoryModel.getCodeBill(), 1,
				inventoryModel.getSuplier(), inventoryModel.getTotalPrice(), inventoryModel.getStaffName());
		ioDao.save(invoice);
		InvoiceProduct ip ;
		IOInventory ioInventory = ioDao.findByCodeBill(invoice.getCodeBill());
		for( ProductModel product : inventoryModel.getProducts()) {
			ip = new InvoiceProduct();
			ip.setIoInventory(new IOInventory(ioInventory.getId()));
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
				p = productDao.findProductByCode(product.getCode());
			}
			ip.setProduct(p);
			ip.setQuantity(product.getQuantity());	
			ipDao.save(ip);
		}
	}
	
	
	public void addInvoiceOutput(InventoryModel inventoryModel) {
		Product p;
		IOInventory invoice = new IOInventory(inventoryModel.getCodeBill(), 2,
				inventoryModel.getSuplier(), inventoryModel.getTotalPrice(), inventoryModel.getStaffName());
		ioDao.save(invoice);
		InvoiceProduct ip ;
		IOInventory ioInventory = ioDao.findByCodeBill(invoice.getCodeBill());
		for( ProductModel product : inventoryModel.getProducts()) {
			ip = new InvoiceProduct();
			ip.setIoInventory(new IOInventory(ioInventory.getId()));
			p = productDao.findProductByCode(product.getCode());
			if(p != null) {
				p.setQuantity(p.getQuantity() - product.getQuantity());
				productDao.update(p);
			}
			ip.setProduct(p);
			ip.setQuantity(product.getQuantity());	
			ipDao.save(ip);
		}
	}
}
