package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.CartsException;
import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Service.CartService;
import com.sweetsdelight_bk.Service.CategoryService;
import com.sweetsdelight_bk.Service.CustomerService;
import com.sweetsdelight_bk.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	 @Autowired
	 private ProductService productService;
	 
	 @Autowired
	 private CategoryService categoryService;
	
	 @Autowired
	 private CartService cartService;
	 
	 
	@PostMapping("/add")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomerDetailsById(@Valid @RequestBody Customer customer,@PathVariable("id") Integer id) throws CustomerException{
		
		return new ResponseEntity<>(customerService.updateCustomer(customer, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer id) throws CustomerException{
		
		return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException{
		return new ResponseEntity<>(customerService.showAllCustomers(),HttpStatus.FOUND);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> showAllCustomerDetails(@PathVariable("id") Integer id) throws CustomerException{
		return new ResponseEntity<>(customerService.showCustomerDetailsById(id),HttpStatus.FOUND);
	}
	
	//product
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProductByName(@RequestParam("productName") String productName)throws ProductException{
		return new ResponseEntity<List<Product>>(productService.searchByName(productName),HttpStatus.OK);
	}
	
	@GetMapping("/product/all")
	public ResponseEntity<List<Product>> allProduct(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize)throws ProductException{
		return new ResponseEntity<List<Product>>(productService.showAllProducts(pageNumber, pageSize),HttpStatus.OK);
	}
	
	@GetMapping("/product/available")
	public ResponseEntity<Page<Product>> allavailableProduct(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize)throws ProductException{
		return new ResponseEntity<Page<Product>>(productService.showAllProductsByAvailable(pageNumber, pageSize),HttpStatus.OK);
	}
	
	//category
	@GetMapping("/category")
	public ResponseEntity<List<Category>> showAllCategory()throws CategoryException{
		return new ResponseEntity<List<Category>>(categoryService.showAllCategory(),HttpStatus.OK);
	}
	
	//cart
	@PutMapping("/cart/{customerId}/{productId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Integer customerId,@PathVariable Integer productId)throws CartsException{
		return new ResponseEntity<Cart>(cartService.addProductToCart(customerId, productId), HttpStatus.CREATED);
	}
	
	@PutMapping("/cart/remove/{customerId}/{productId}")
	public ResponseEntity<Cart> removeProductToCart(@PathVariable Integer customerId,@PathVariable Integer productId)throws CartsException{
		return new ResponseEntity<Cart>(cartService.removeProductByCart(customerId, productId), HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/{customerId}")
	public ResponseEntity<List<Product>> showCartsProduct(@PathVariable Integer customerId){
		return new ResponseEntity<List<Product>>(cartService.getProductByCartId(customerId),HttpStatus.OK);
	}
	
}
