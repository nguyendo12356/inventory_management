package com.java.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;

@Data
public class InventoryModel {

	private int id;
	private String codeBill;
	private int type;
	private String suplier;
	private double totalPrice;
	private String staffName;
	List<ProductModel> products = new LinkedList<ProductModel>();

}
