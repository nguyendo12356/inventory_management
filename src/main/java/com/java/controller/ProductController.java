package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Common;
import com.java.model.ProductModel;
import com.java.service.CategoryService;
import com.java.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Common common;
	
	@GetMapping(value = "/list/{pageNumber}/{pageSize}")
	public ModelAndView findAll(HttpServletRequest request, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
		ModelAndView modal = new ModelAndView();
		if(common.checkSession(request)) {
			modal.setViewName("redirect:/loginForm");
		}else {
			modal.setViewName("productlist");
			int totalProduct = productService.totalProduct();
			boolean isLegal = true;
			if(pageNumber < 1) {
				pageNumber = 1;
				isLegal = false;
			}else if (pageNumber > totalProduct) {
				pageNumber = totalProduct;
				isLegal = false;
			}
			if (pageSize > 50) {
				pageSize = 50;
				isLegal = false;
			}else if (pageSize == -1) {
				pageSize = totalProduct;
				isLegal = false;
			}
			if(!isLegal) {
				modal.setViewName("redirect:/product/list/"+pageNumber+"/"+pageSize);
				return modal;
			}
			modal.addObject("products", productService.getCountRecord( (pageNumber - 1)*pageSize , pageSize));
			modal.addObject("totalProduct", totalProduct);
		}
		return modal;
	}
	
	@GetMapping(value = "/form")
	public ModelAndView showForm() {
		ModelAndView modal = new ModelAndView("addproduct");
		modal.addObject("category", categoryService.findAll());
		modal.addObject("product", new ProductModel());
		return modal;
	}
	
	@PostMapping(value = "/add")
	public ModelAndView addProduct(@ModelAttribute("product") ProductModel productModel, HttpServletRequest request) {
		ModelAndView modal = new ModelAndView("redirect:/product/list/1/4");
		productService.saveProduct(productModel, request.getServletContext().getRealPath("static\\images"));
		return modal;
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delProduct(@PathVariable("id") int id) {
		ModelAndView modal = new ModelAndView("redirect:/product/list/1/4");
		productService.deleteProduct(id);
		return modal;
	}
	
}
