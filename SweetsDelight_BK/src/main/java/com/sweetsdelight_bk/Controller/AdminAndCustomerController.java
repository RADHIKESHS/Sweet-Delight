package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Service.AdminService;
import com.sweetsdelight_bk.Service.CategoryService;
import com.sweetsdelight_bk.Service.CustomerService;
import com.sweetsdelight_bk.Service.ProductService;

@RestController
@RequestMapping("/sweetDelight")
public class AdminAndCustomerController {
	
	@Autowired
	private AdminService adminService;
   
	@Autowired
	private ProductService productService;
   
	@Autowired
	private CategoryService categoryService;
   
	@Autowired
	private CustomerService customerservice;
   
	 
	
	
    	@DeleteMapping("/delete/{customerid}")  // admin && user
    	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerid") Integer customerid) throws CustomerException{
		
    		return new ResponseEntity<>(customerservice.deleteCustomer(customerid),HttpStatus.OK);
    	}
    
    	@GetMapping("/products")     //admin  & user 
    	public ResponseEntity<List<Product>> getAllProducts() throws ProductException {

    		List<Product> products =adminService.allProducts();
    		return new ResponseEntity<>(products, HttpStatus.FOUND);

    	}

    	@GetMapping("/categories")   //admin & user 
    	public ResponseEntity<List<Category>> getAllCategories() {

    		List<Category> categories =adminService.allCategories();
    		return new ResponseEntity<>(categories, HttpStatus.FOUND);

    	}
	
    	@GetMapping("/categories/{id}")    //admin && user
    	public ResponseEntity<Category> showCategoryById(@PathVariable("id") Integer id) {
		
    		Category category = categoryService.showCategoryById(id);
		
    		return new ResponseEntity<>(category, HttpStatus.OK);
    	}
	
	
    	@GetMapping("/products/sorted")     //admin && user 
    	public ResponseEntity<List<Product>> getAllProductsWithSort(
    			@RequestParam(defaultValue = "0") int pageNumber,
    			@RequestParam(defaultValue = "50") int pageSize,
    			@RequestParam(defaultValue = "productName") String sortBy,
    			@RequestParam(defaultValue = "ASC") String sortDirection
    			) 
    					throws ProductException {
        		List<Product> products = productService.showAllProductswithsort(pageNumber, pageSize, sortBy, sortDirection);
        		return ResponseEntity.ok(products);
    	}
    
		//category
		@GetMapping("/category")     //admin && user
		public ResponseEntity<List<Category>> showAllCategory()throws CategoryException{
			return new ResponseEntity<List<Category>>(categoryService.showAllCategory(),HttpStatus.OK);
		}
		
		
		//product
		@GetMapping("/searchbyname")      // user && admin
		public ResponseEntity<List<Product>> searchProductByName(@RequestParam("productName") String productName)throws ProductException{
			return new ResponseEntity<List<Product>>(productService.searchByName(productName),HttpStatus.OK);
		}
		
		@GetMapping("/product/getallproduct")    //user && admin 
		public ResponseEntity<List<Product>> allProduct(
			        @RequestParam(defaultValue = "0") int pageNumber,
			        @RequestParam(defaultValue = "50") int pageSize
			) 
			throws ProductException {
		    	return new ResponseEntity<>(productService.showAllProducts(pageNumber, pageSize), HttpStatus.OK);
		}
		
		@GetMapping("/product/getallavailableproduct")     //admin && user 
		public ResponseEntity<Page<Product>> allavailableProduct(
			        @RequestParam(defaultValue = "0") int pageNumber,
			        @RequestParam(defaultValue = "50") int pageSize
			) 
			throws ProductException{
		    	return new ResponseEntity<Page<Product>>(productService.showAllProductsByAvailable(pageNumber, pageSize), HttpStatus.OK);
		}
		
}
