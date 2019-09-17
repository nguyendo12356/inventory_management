package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "auth")
public class Auth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@ManyToOne
	@JoinColumn( name = "menu_id", nullable = false)
	private Menu menu;
	
	@Column(name = "permission")
	private boolean permission;
	
	@Column(name = "active_flag")
	private boolean active;
	
	@Column(name = "create_date")
	private Date createDate;
}
