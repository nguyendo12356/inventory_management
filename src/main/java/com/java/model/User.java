package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private boolean gender;
	
	private String image;
	
	private boolean active;
	
	private Date createDate;
}
