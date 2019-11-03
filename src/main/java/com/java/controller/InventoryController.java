package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.IOInventory;
import com.java.model.InventoryModel;
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
		return modal;
	}
	
	@RequestMapping(value = {"/input/save"}, method = RequestMethod.POST)
	public ModelAndView addInvoice(@ModelAttribute("model") InventoryModel model, HttpServletRequest request) {
		ModelAndView modal = new ModelAndView("redirect:/inventory/input");
		inventoryService.addInvoice(model);
		return modal;
	}
	
	@RequestMapping(value = {"/input/details/{id}"}, method = RequestMethod.GET)
	public ModelAndView addInvoice(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("invoice-details");
		IOInventory ioInventory = ioService.findIOInventoryById(id);
		model.addObject("invoiceDetail", ioInventory);
		return model;
	}
}
