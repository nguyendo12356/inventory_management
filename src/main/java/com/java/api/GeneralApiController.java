package com.java.api;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.InvoiceProductDao;
import com.java.entity.Category;
import com.java.entity.InvoiceProduct;
import com.java.entity.Product;
import com.java.model.ProductModel;
import com.java.service.CategoryService;
import com.java.service.ProductService;

@RestController
@RequestMapping("/api")
@Transactional
public class GeneralApiController {

	@Autowired
	private CategoryService categorySer;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private InvoiceProductDao ipDao;
	
	@PostMapping(value = "/category")
	public ResponseEntity<String> addNewCategory(
			@RequestParam("categoryName") String name,
			@RequestParam("categoryDescription") String description){
		if(categorySer.findByName(name) == null) {
			categorySer.addCaterory(name,description);
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("category_exists",HttpStatus.OK);
		}
	}
	

	@GetMapping(value = "/categoryType")
	@ResponseBody
	public ResponseEntity<List<Product>> findByProductByCategory(@RequestParam("cateId") int cateId){
		return new ResponseEntity<List<Product>>(new LinkedList<Product>(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/invoice/product")
	@ResponseBody
	public ResponseEntity<List<InvoiceProduct>> getIProduct(){
		return new ResponseEntity<List<InvoiceProduct>>(ipDao.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/product")
	public ResponseEntity<ProductModel> getProductById(@RequestParam("id") int id){
		return new ResponseEntity<ProductModel>(productService.findProductById(id), HttpStatus.OK);
	}
}
