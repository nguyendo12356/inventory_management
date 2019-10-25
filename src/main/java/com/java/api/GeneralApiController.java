package com.java.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.service.CategoryService;

@RestController
@RequestMapping("/api")
@Transactional
public class GeneralApiController {

	@Autowired
	private CategoryService categorySer;
	
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
}
