package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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
	
	@Column(name = "staff")
	private String staffName;
	
	public IOInventory(String codeBill, int type, String suplier, double price, String staffName) {
		this.codeBill = codeBill;
		this.type = type;
		this.suplier = suplier;
		this.price = price;
		this.staffName = staffName;
		this.createDate = new Date();
	}

}
