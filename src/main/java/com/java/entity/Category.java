package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
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
	private boolean isActive;
	@Column(name = "create_date")
	private Date createDate;
}
