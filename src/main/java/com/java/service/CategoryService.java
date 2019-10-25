package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CategoryDao;
import com.java.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
	public Category findByName(String name){
		return categoryDao.findByName(name);
	}
	
	public void addCaterory(String name,String description) {
		Category category = new Category(name, description);
		categoryDao.save(category);
	}
	
	public void delCaterory(int id) {
		categoryDao.delete(Category.class, id);
	}
	
	public int getSumRecords() {
		return categoryDao.countRecord();
	}
}
