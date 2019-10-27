package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
	@Id
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "description")
	private String description;
	@Column(name = "active_flag")
	private boolean active;
	@Column(name = "create_date")
	private Date createDate;
	
	public Category(String name, String description) {
		this.active = true;
		this.createDate = new Date();
		this.name = name;
		this.description = description;
	}
	
	public Category(int id) {
		this.id = id;
	}
}
