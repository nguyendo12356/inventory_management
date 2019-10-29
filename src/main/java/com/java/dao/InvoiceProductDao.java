package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.InvoiceProduct;

public interface InvoiceProductDao extends BaseDao<InvoiceProduct> {

	public List<InvoiceProduct> getAll();
}
