package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.InventoryModel;
import com.java.model.ProductModel;
import com.java.model.User;
import com.java.service.CategoryService;
import com.java.service.IOService;
import com.java.service.InventoryService;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {
	
	@Autowired
	private IOService ioService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value = {"/input"}, method = RequestMethod.GET)
	public ModelAndView listInput() {
		ModelAndView modal = new ModelAndView("inputInventoryList");
		modal.addObject("inputList", ioService.getAll());
		return modal;
	}
	
	@RequestMapping(value = {"/input/add"}, method = RequestMethod.GET)
	public ModelAndView addInvoiceForm() {
		ModelAndView modal = new ModelAndView("inputInventoryAdd");
		modal.addObject("category", categoryService.findAll());
		InventoryModel inventoryModel = new InventoryModel();
		ProductModel p = new ProductModel();
		inventoryModel.getProducts().add(p);
		p = new ProductModel();
		inventoryModel.getProducts().add(p);
		p = new ProductModel();
		inventoryModel.getProducts().add(p);
		p = new ProductModel();
		inventoryModel.getProducts().add(p);
		modal.addObject("model", inventoryModel);
		modal.addObject("category", categoryService.findAll());
		return modal;
	}
	
	@RequestMapping(value = {"/input/save"}, method = RequestMethod.POST)
	public ModelAndView addInvoice(@ModelAttribute("model") InventoryModel model, HttpServletRequest request) {
		ModelAndView modal = new ModelAndView("redirect:/inventory/input");
		User u = (User)request.getSession().getAttribute("session");
		model.setStaffName(u.getName() != null ? u.getName() : "");
		inventoryService.addInvoice(model);
		return modal;
	}
}
