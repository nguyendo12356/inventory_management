package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class Auth {

	private int id;
	private Role role;
	private Menu menu;
	private boolean permission;
	private boolean active;
	private Date createDate;
}
