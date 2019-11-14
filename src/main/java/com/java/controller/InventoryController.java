package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.IOInventory;
import com.java.entity.Product;
import com.java.model.InventoryModel;
import com.java.service.CategoryService;
import com.java.service.IOService;
import com.java.service.InventoryService;
import com.java.service.ProductService;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {
	
	@Autowired
	private IOService ioService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value = {"/input"}, method = RequestMethod.GET)
	public ModelAndView listInput() {
		ModelAndView modal = new ModelAndView("inputInventoryList");
		modal.addObject("inputList", ioService.getAll(1));
		return modal;
	}
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public ModelAndView inventoryList() {
		ModelAndView modal = new ModelAndView("inventoryList");
		modal.addObject("categorys", categoryService.findAll());
		return modal;
	}
	
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
	public ModelAndView inventoryList1(@PathVariable("id") String code) {
		ModelAndView modal = new ModelAndView("inventoryList");
		modal.addObject("categorys", categoryService.findAll());
		modal.addObject("product", productService.getProductByCode(code));
		return modal;
	}
	
	@RequestMapping(value = {"/ajax/list"}, method = RequestMethod.GET)
	public ModelAndView inventoryDataList(@RequestParam("id") int id, @RequestParam("status") String status) {
		ModelAndView modal = new ModelAndView("inventory/table-product");
		List<Product> list;
		list = productService.getProductByCategory(id, status);
		modal.addObject("productList", list);
		return modal;
	}
	
	@RequestMapping(value = {"/output"}, method = RequestMethod.GET)
	public ModelAndView listOutput() {
		ModelAndView modal = new ModelAndView("outputInventoryList");
		modal.addObject("inputList", ioService.getAll(2));
		return modal;
	}
	
	@RequestMapping(value = {"/input/add"}, method = RequestMethod.GET)
	public ModelAndView addInvoiceForm() {
		ModelAndView modal = new ModelAndView("inputInventoryAdd");
		modal.addObject("category", categoryService.findAll());
		return modal;
	}
	
	@RequestMapping(value = {"/output/add"}, method = RequestMethod.GET)
	public ModelAndView outputAdd() {
		ModelAndView modal = new ModelAndView("outputInventoryAdd");
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
	public ModelAndView invoiceDetail(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("invoice-details");
		IOInventory ioInventory = ioService.findIOInventoryById(id, 1);
		model.addObject("invoiceDetail", ioInventory);
		return model;
	}
	
	@RequestMapping(value = {"/output/details/{id}"}, method = RequestMethod.GET)
	public ModelAndView outputDetail(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("output-invoice-detail");
		IOInventory ioInventory = ioService.findIOInventoryById(id, 2);
		model.addObject("invoiceDetail", ioInventory);
		return model;
	}

}
