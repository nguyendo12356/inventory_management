package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.IOInventory;
import com.java.model.User;
import com.java.service.IOService;

@Controller
public class HomeController {

	@Autowired
	private IOService ioService;
	
	@RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if(request.getSession().getAttribute("session") == null) {
			request.setAttribute("user", new User());
			return "login";
		}
		//ioService.getRevenue().forEach(System.out::println);
		return "home";
	}
	
	@RequestMapping(value = {"/export"}, method = RequestMethod.GET)
	public ModelAndView exportPdf( ModelMap model, @RequestParam("invoice") int invoiceId ) {
		IOInventory ioInventory = ioService.findIOInventoryById(invoiceId , 1);
		ModelAndView modelAndView = new ModelAndView("pdfView","model", ioInventory);
		return modelAndView;
	}
}
