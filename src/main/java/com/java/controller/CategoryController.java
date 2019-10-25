package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.service.CategoryService;

@Controller
@RequestMapping("/product")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/category")
	public String getCategory(ModelMap modal) {
		modal.addAttribute("sumCategory", categoryService.getSumRecords());
		modal.addAttribute("category", categoryService.findAll());
		return "categorylist";
	}
	
	@GetMapping(value = "/category/delete/{id}")
	public String delCategory(@PathVariable("id")int id) {
		categoryService.delCaterory(id);
		return "redirect:/product/category";
	}
	
}
