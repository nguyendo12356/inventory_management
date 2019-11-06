package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.Product;

public interface ProductDao extends BaseDao<Product> {
	
	Product findProductByCode(String code);
	
	List<String> showCodeByLetter(String code);

	List<Object[]> getQuantityByCategory();
}
