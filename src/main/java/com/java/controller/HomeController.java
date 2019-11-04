package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.IOInventory;
import com.java.model.InventoryModel;
import com.java.model.User;

@Controller
public class HomeController {

	@RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if(request.getSession().getAttribute("session") == null) {
			request.setAttribute("user", new User());
			return "login";
		}
		return "home";
	}
	
	@RequestMapping(value = {"/export"}, method = RequestMethod.GET)
	public ModelAndView exportPdf( ModelMap model, @RequestParam("invoice") IOInventory ioInventory ) {
		InventoryModel inventoryModel = new InventoryModel();
		inventoryModel.setCodeBill("A1234");
		IOInventory m = ioInventory;
		System.out.println(m);
		ModelAndView modelAndView = new ModelAndView("pdfView","model", inventoryModel);
		return modelAndView;
	}
}
