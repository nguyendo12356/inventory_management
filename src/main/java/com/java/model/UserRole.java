package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserRole {

	private int id;
	private User user;
	private Role role;
	private boolean active;
	private Date createDate;

}
