package com.java.model;

import java.util.Date;

import lombok.Data;

@Data
public class Role {

	private int id;
	
	private String roleName;
	
	private String desciption;
	
	private int active;
	
	private Date createDate;
}
