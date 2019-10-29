package com.java.util;

import java.util.Date;

import com.java.entity.Category;
import com.java.entity.Menu;
import com.java.entity.Product;
import com.java.entity.UserDto;
import com.java.model.MenuModel;
import com.java.model.ProductModel;
import com.java.model.User;

public class ConvertObject {
	
	public static UserDto convertUserToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setGender(user.isGender());
		userDto.setImage(user.getImage().getOriginalFilename());
		userDto.setActive(user.isActive());
		userDto.setCreateDate(user.getCreateDate());
		user.getRole().forEach( n ->{ userDto.getUserRole().add(n);});
		return userDto;
	}
	
	public static User convertUserDtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setGender(userDto.isGender());
		user.setImageName(userDto.getImage());
		user.setActive(userDto.isActive());
		user.setCreateDate(userDto.getCreateDate());
		if(!userDto.getUserRole().isEmpty()) {
			System.out.println(user);
			userDto.getUserRole().forEach( n ->{ user.getRole().add(n);});
		}
		return user;
	}
	
	public static MenuModel convertMenu(Menu menu) {
		MenuModel menuModel = new MenuModel();
		menuModel.setActive(menu.isActive());
		menuModel.setCreateDate(menu.getCreateDate());
		menuModel.setId(menu.getId());
		menuModel.setIndex(menu.getIndex());
		menuModel.setName(menu.getName());
		menuModel.setParent_id(menu.getParent_id());
		menuModel.setUrl(menu.getUrl());
		return menuModel;
	}
	
	public static Menu convertMenuDto(MenuModel menuModel) {
		Menu menu = new Menu();
		menu.setActive(menuModel.isActive());
		menu.setCreateDate(menuModel.getCreateDate());
		menu.setId(menuModel.getId());
		menu.setIndex(menuModel.getIndex());
		menu.setName(menuModel.getName());
		menu.setParent_id(menuModel.getParent_id());
		menu.setUrl(menuModel.getUrl());
		return menu;
	}
	
	public static Product parseProduct(ProductModel productModel) {
		Product p = new Product();
		p.setCategory(new Category(productModel.getCategory()));
		p.setDescription(productModel.getDescription());
		p.setDiscount(productModel.getDiscount());
		p.setName(productModel.getName());
		p.setPrice(productModel.getPrice());
		p.setQuantity(productModel.getQuantity());
		p.setActive(true);
		p.setCreateDate(productModel.getCreateDate() == null ? new Date() : productModel.getCreateDate());
		return p;
	}
	
	public static ProductModel parseProductModel(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setCategory(product.getCategory().getId());
		productModel.setDescription(product.getDescription());
		productModel.setDiscount(product.getDiscount());
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setQuantity(product.getQuantity());
		productModel.setActive(true);
		productModel.setImageName(product.getImg_url());
		productModel.setCreateDate(product.getCreateDate() == null ? new Date() : product.getCreateDate());
		return productModel;
	}
	
}
