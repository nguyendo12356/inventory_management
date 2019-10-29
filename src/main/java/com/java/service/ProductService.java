package com.java.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.common.Constant;
import com.java.dao.ProductDao;
import com.java.entity.Product;
import com.java.model.ProductModel;
import com.java.util.ConvertObject;
import com.java.util.Util;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	public List<Product> findAll(){
		return productDao.findAll();
	}
	
	public List<Product> getCountRecord(int page, int num){
		return productDao.getCountRecord(page, num);
	}
	
	public int totalProduct(){
		return productDao.countRecord();
	}
	
	public String saveProduct(ProductModel productModel, String img_url) {
		Product product = ConvertObject.parseProduct(productModel); 
		try {
			if(!Util.saveImage(new File(img_url) , productModel.getImg_url())) {
				return Constant.ERROR_IMAGE;
			}else {
				product.setImg_url(productModel.getImg_url().getOriginalFilename());
				productDao.save(product);
				return Constant.SUCCESS;
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constant.SUCCESS;
		
	}
	
	public void deleteProduct(int id) {
		productDao.delete(Product.class, id);
	}
	
	public ProductModel findProductById(int id) {
		return ConvertObject.parseProductModel(productDao.findById(Product.class, id));
	}
	
}
