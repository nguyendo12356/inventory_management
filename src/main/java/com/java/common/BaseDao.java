package com.java.common;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E> {
	List<E> findAll();

	E findById(Class<E> e, Serializable id);

	List<E> findByProperty(String property, Object value);

	void save(E instance);

	void update(E instance);
}