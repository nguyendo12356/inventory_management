package com.java.model;

import java.util.Date;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.java.entity.Role;

import lombok.Data;

@Data
public class User {

	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private boolean gender;
	
	private MultipartFile image;
	
	private String imageName;
	
	private boolean active;
	
	private Date createDate;
	
	private Set<Role> role;
}
