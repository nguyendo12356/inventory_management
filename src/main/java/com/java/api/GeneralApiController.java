package com.java.api;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.InvoiceProductDao;
import com.java.entity.InvoiceProduct;
import com.java.entity.Product;
import com.java.model.InventoryModel;
import com.java.model.ProductModel;
import com.java.model.User;
import com.java.service.CategoryService;
import com.java.service.IOService;
import com.java.service.InventoryService;
import com.java.service.ProductService;
import com.java.service.UserService;
import com.java.util.Util;

@RestController
@RequestMapping("/api")
@Transactional
public class GeneralApiController {

	@Autowired
	private CategoryService categorySer;

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InvoiceProductDao ipDao;

	@Autowired
	private IOService ioService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/category")
	public ResponseEntity<String> addNewCategory(@RequestParam("categoryName") String name,
			@RequestParam("categoryDescription") String description) {
		if (categorySer.findByName(name) == null) {
			categorySer.addCaterory(name, description);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("category_exists", HttpStatus.OK);
		}
	}

	@GetMapping(value = "/categoryType")
	@ResponseBody
	public ResponseEntity<List<Product>> findByProductByCategory(@RequestParam("cateId") int cateId) {
		return new ResponseEntity<List<Product>>(new LinkedList<Product>(), HttpStatus.OK);
	}

	@GetMapping(value = "/invoice/product")
	@ResponseBody
	public ResponseEntity<List<InvoiceProduct>> getIProduct() {
		return new ResponseEntity<List<InvoiceProduct>>(ipDao.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/product")
	public ResponseEntity<ProductModel> getProductById(@RequestParam("id") int id) {
		return new ResponseEntity<ProductModel>(productService.findProductById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/productbycode")
	public ResponseEntity<ProductModel> getProductByName(@RequestParam("code") String code) {
		ProductModel p = productService.findProductByCode(code);
		return new ResponseEntity<ProductModel>(p != null ? p : new ProductModel(), HttpStatus.OK);
	}

	@GetMapping(value = "/productbyletter")
	public ResponseEntity<List<String>> getCodeByLetter(@RequestParam("code") String code) {
		List<String> codes = productService.showCodeByLetter(code);
		return new ResponseEntity<List<String>>(codes, HttpStatus.OK);
	}

	@PostMapping(value = "/addInvoice")
	@ResponseBody
	public ResponseEntity<InventoryModel> addInvoice(@RequestBody InventoryModel model, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("session");
		model.setStaffName(u != null ? u.getName() : "");
		inventoryService.addInvoice(model);
		return new ResponseEntity<InventoryModel>(model, HttpStatus.OK);
	}

	@GetMapping("/category/quantity")
	@ResponseBody
	public ResponseEntity<List<Object[]>> test() {
		List<Object[]> list = productService.getQuantityByCategory();
		return new ResponseEntity<List<Object[]>>(list, HttpStatus.OK);
	}

	@GetMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestParam("username") String username) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			return new ResponseEntity<String>("user_not_exits", HttpStatus.OK);
		} else {
			String encodeUsername = Base64.encode(user.getUsername().getBytes());
			return new ResponseEntity<String>(Util.sendEmail(user.getEmail(), encodeUsername), HttpStatus.OK);
		}
	}

	@GetMapping("/productByCategory")
	public ResponseEntity<List<Product>> getProductByCategory(@RequestParam("id") int id) {
		List<Product> list = productService.getProductByCategory(id);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

}
