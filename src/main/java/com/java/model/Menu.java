package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class Menu {

	private int id;
	private int parent_id;
	private String url;
	private String name;
	private int index;
	private boolean active;
	private Date createDate;
}
