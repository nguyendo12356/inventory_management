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

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "description")
	private String desciption;
	
	@Column(name = "active_flag")
	private boolean active;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany( mappedBy = "role")
	private Collection<UserRole> user = new HashSet<UserRole>(); 
	
	@OneToMany( mappedBy = "role")
	private Collection<Auth> auth = new HashSet<Auth>(); 
	
//	@ManyToMany
//	@JoinTable(name = "auth", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
//	private Collection<Menu> menu = new HashSet<Menu>(); 
}
