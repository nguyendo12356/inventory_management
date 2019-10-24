package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.service.IOService;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {
	
	@Autowired
	private IOService ioService;
	
	@RequestMapping(value = {"/input"}, method = RequestMethod.GET)
	public ModelAndView listInput() {
		ModelAndView modal = new ModelAndView("inputInventoryList");
		modal.addObject("inputList", ioService.getAll());
		return modal;
	}
	
	@RequestMapping(value = {"/input/add"}, method = RequestMethod.GET)
	public ModelAndView addInvoice() {
		ModelAndView modal = new ModelAndView("inputInventoryAdd");
		return modal;
	}
}
