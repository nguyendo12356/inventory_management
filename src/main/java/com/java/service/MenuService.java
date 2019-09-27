package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MenuDao;
import com.java.entity.Menu;

@Service
public class MenuService {

	@Autowired
	private MenuDao<Menu> menuDao;
	public List<Menu> findByProperty(String property, Object value){
		return menuDao.findByProperty(property, value);
	}
	
	public List<Menu> findAll(){
		return menuDao.findAll();
	}
	
	
	public void addMenu(Menu menu) {
		menuDao.save(menu);
	}
	
	public void deleteMenu(int id) {
		menuDao.delete(Menu.class, id);
	}
	
	public void updateMenu(Menu menu) {
		menuDao.update(menu);
	}
}
