package com.java.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuModel {

	private int id;
	private int parent_id;
	private String url;
	private String name;
	private int index;
	private boolean active;
	private Date createDate;
	private List<MenuModel> childMenu = new ArrayList<MenuModel>();
	private String idMenu;
	
	public MenuModel(String name, int parent_id, String url, int index, boolean active, Date createDate){
		this.name = name;
		this.parent_id = parent_id;
		this.url = url;
		this.index = index;
		this.active = active;
		this.createDate = createDate;
	}
}
