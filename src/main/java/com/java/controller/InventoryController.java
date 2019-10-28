package com.java.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.InventoryModel;
import com.java.model.ProductModel;
import com.java.service.CategoryService;
import com.java.service.IOService;
import com.java.service.ProductService;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {
	
	@Autowired
	private IOService ioService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = {"/input"}, method = RequestMethod.GET)
	public ModelAndView listInput() {
		ModelAndView modal = new ModelAndView("inputInventoryList");
		modal.addObject("inputList", ioService.getAll());
		return modal;
	}
	
	@RequestMapping(value = {"/input/add"}, method = RequestMethod.GET)
	public ModelAndView addInvoice() {
		ModelAndView modal = new ModelAndView("inputInventoryAdd");
		modal.addObject("category", categoryService.findAll());
		InventoryModel inventoryModel = new InventoryModel();
		inventoryModel.setCodeBill("A132");
		inventoryModel.setSuplier("Cty Abcs");
		ProductModel p = new ProductModel();
		p.setCategory(0);
		p.setDescription("");
		p.setDiscount(0);
		p.setName("");
		p.setPrice(0);
		p.setQuantity(0);
		p.setActive(true);
		p.setCreateDate(new Date());
//		
//		ProductModel p1 = new ProductModel();
//		p1.setCategory(2);
//		p1.setDescription("mo ta 2");
//		p1.setDiscount(7);
//		p1.setName("NAme 2");
//		p1.setPrice(900);
////		p1.setQuantity(12);
//		p1.setActive(true);
//		p1.setCreateDate(new Date());
//		
		inventoryModel.getProducts().add(p);
//		inventoryModel.getProducts().add(p1);
		
		modal.addObject("model", inventoryModel);
		modal.addObject("productlist", productService.findAll());
		modal.addObject("category", categoryService.findAll());
		return modal;
	}
}
