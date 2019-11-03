package com.java.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table( name = "ioinvoice_product")
public class InvoiceProduct implements Serializable {
	
	private static final long serialVersionUID = -2186499300593797337L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;
	
	@ManyToOne
	@JoinColumn( name = "id_invoice")
	private IOInventory ioInventory;
	
	@Column(name = "quantity")
	private int quantity;

}
