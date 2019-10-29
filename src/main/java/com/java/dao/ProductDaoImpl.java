package com.java.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.java.common.BaseDaoImpl;
import com.java.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{
	
}
