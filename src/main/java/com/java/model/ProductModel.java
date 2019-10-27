package com.java.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductModel {
	private int id;
	private int category;
	private String name;
	private int quantity;
	private double price;
	private String discount;
	private String code;
	private MultipartFile img_url;
	private String description;
	private boolean active;
	private Date createDate;
}
