package com.java.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public UserDto(int id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;	
		
	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "active_flag")
	private boolean active;
	
	@Column(name = "create_date" ,nullable = true)
	private Date createDate;
	
	@OneToMany(mappedBy = "userDto")
	private Set<UserRole> userRole = new HashSet<UserRole>();
}
