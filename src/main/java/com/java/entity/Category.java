package com.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1462340105459527545L;
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
	
	@JsonIgnore
	@OneToMany( mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<Product>(); 
	
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
