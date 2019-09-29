package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthModel {

	private int id;
	private Role role;
	private MenuModel menu;
	private boolean permission;
	private boolean active;
	private Date createDate;
}
