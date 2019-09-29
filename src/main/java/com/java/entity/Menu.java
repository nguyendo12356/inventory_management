package com.java.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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

	@OneToMany( mappedBy = "menu")
	private Collection<Auth> auth = new HashSet<Auth>(); 	
	
//	@ManyToMany( mappedBy = "menu")
//	private Collection<Role> role = new HashSet<Role>(); 
	
	public Menu(String name, int parent_id, String url, int index, boolean active, Date createDate){
		this.name = name;
		this.parent_id = parent_id;
		this.url = url;
		this.index = index;
		this.active = active;
		this.createDate = createDate;
	}
}
