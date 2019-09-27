package com.java.dao;

import com.java.common.BaseDao;

public interface MenuDao<E> extends BaseDao<E> {
	
	void deleteMenu(int id);
}
