package com.java.api;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

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
import com.java.entity.IOInventory;
import com.java.entity.InvoiceProduct;
import com.java.entity.Product;
import com.java.model.InventoryModel;
import com.java.model.ProductModel;
import com.java.model.User;
import com.java.service.CategoryService;
import com.java.service.InventoryService;
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
	private InventoryService inventoryService;
	
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
	
	@GetMapping(value = "/productbycode")
	public ResponseEntity<ProductModel> getProductByName(@RequestParam("code") String code){
		ProductModel p = productService.findProductByCode(code);
		return new ResponseEntity<ProductModel>(p != null ? p : new ProductModel(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/productbyletter")
	public ResponseEntity<List<String>> getCodeByLetter(@RequestParam("code") String code){
		List<String> codes = productService.showCodeByLetter(code);
		return new ResponseEntity<List<String>>(codes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/addInvoice")
	@ResponseBody
	public ResponseEntity<InventoryModel> addInvoice(@RequestBody InventoryModel model, HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("session");
		model.setStaffName(u != null ? u.getName() : "");
		System.out.println(u.getName());
		inventoryService.addInvoice(model);
		return new ResponseEntity<InventoryModel>(model, HttpStatus.OK);
	}
	
	@PostMapping("/test")
	public ResponseEntity<String> test(){
		InvoiceProduct model = new InvoiceProduct();
		IOInventory io = new IOInventory();
		io.setId(17);
		model.setIoInventory(io);
		Product p = new Product();
		p.setId(7);
		model.setProduct(p);
		ipDao.save(model);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
