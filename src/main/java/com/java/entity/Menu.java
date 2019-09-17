package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "menu")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "parent_id")
	private int parent_id;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "order_index")
	private int index;
	
	@Column(name = "active_flag")
	private boolean active;
	
	@Column(name = "create_date")
	private Date createDate;
}
