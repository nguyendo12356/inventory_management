package com.java.dao;

import com.java.common.BaseDao;
import com.java.entity.Category;

public interface CategoryDao extends BaseDao<Category> {

	Category findByName(String name);
	
	Category findCategoryById(int id);
}
