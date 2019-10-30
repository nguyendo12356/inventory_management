package com.java.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.java.entity.Category;

import lombok.Data;

@Data
public class ProductModel {
	private int id;
	private int category;
	private Category cate;
	private String name;
	private int quantity;
	private double price;
	private int discount;
	private String code;
	private MultipartFile img_url;
	private String description;
	private String imageName;
	private boolean active;
	private Date createDate;
}
