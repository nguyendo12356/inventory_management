package com.java.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "product_info")
public class Product {
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "cate_id")
	private Category category;
	
	@Column(name = "name")
	private String name;
	@Column(name = "qty")
	private int quantity;
	@Column(name = "price")
	private double price;
	@Column(name = "discount")
	private int discount;
	@Column(name = "code")
	private String code;
	@Column(name = "img_url")
	private String img_url;
	@Column(name = "description")
	private String description;
	@Column(name = "active_flag")
	private boolean active;
	@Column(name = "create_date")
	private Date createDate;
	
	@JsonIgnore
	@OneToMany( mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<InvoiceProduct> invoiceProduct = new ArrayList<InvoiceProduct>(); 
	
}
