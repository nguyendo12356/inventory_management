package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "ioinvoice")
public class IOInventory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(name = "codeBill")
	private String codeBill;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "suplier")
	private String suplier;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "create_date")
	private Date createDate;

}
