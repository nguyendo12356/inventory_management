package com.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	@OneToMany(mappedBy = "ioInventory", cascade=CascadeType.ALL)
	//@JsonManagedReference
	@JsonIgnore
	private List<InvoiceProduct> ips = new ArrayList<InvoiceProduct>();
	
	public IOInventory(String codeBill, int type, String suplier, double price, String staffName) {
		this.codeBill = codeBill;
		this.type = type;
		this.suplier = suplier;
		this.price = price;
		this.staffName = staffName;
		this.createDate = new Date();
	}
	
	public IOInventory(int id) {
		this.id = id;
	}

}
