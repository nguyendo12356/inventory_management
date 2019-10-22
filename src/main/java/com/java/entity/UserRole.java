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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public UserRole() {
		this.active = true;
		this.createDate = new Date();
	}
	
	public UserRole(int id) {
		this.id = id;
		this.active = true;
		this.createDate = new Date();
	}
	
	public UserRole(UserDto user, Role role) {
		this.userDto = user;
		this.role = role;
		this.active = true;
		this.createDate = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDto userDto;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "active_flag")
	private boolean active;
	
	@Column( name = "create_date")
	private Date createDate;
	
}
