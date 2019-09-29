package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MenuDao;
import com.java.entity.Menu;
import com.java.model.MenuModel;
import com.java.util.ConvertObject;

@Service
public class MenuService {

	@Autowired
	private MenuDao<Menu> menuDao;
	public List<MenuModel> findByProperty(String property, Object value){
		List<Menu> list = menuDao.findByProperty(property, value);
		List<MenuModel> menu = new ArrayList<MenuModel>();
		list.forEach(item -> menu.add(ConvertObject.convertMenu(item)));
		return menu;
	}
	
	public List<MenuModel> findAll(){
		List<Menu> list = menuDao.findAll();
		List<MenuModel> menu = new ArrayList<MenuModel>();
		list.forEach(item -> menu.add(ConvertObject.convertMenu(item)));
		return menu;
	}
	
	
	public void addMenu(MenuModel menu) {
		menuDao.save(ConvertObject.convertMenuDto(menu));
	}
	
	public void deleteMenu(int id) {
		menuDao.delete(Menu.class, id);
	}
	
	public void updateMenu(Menu menu) {
		menuDao.update(menu);
	}
}
