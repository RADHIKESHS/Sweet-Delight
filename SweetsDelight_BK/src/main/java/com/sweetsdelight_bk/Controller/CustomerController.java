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
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Service.CartService;
import com.sweetsdelight_bk.Service.CategoryService;
import com.sweetsdelight_bk.Service.CustomerService;
import com.sweetsdelight_bk.Service.ProductService;
import com.sweetsdelight_bk.Service.SweetOrderService;

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
	 
	 @Autowired
	 private SweetOrderService sweetservice;
	 
	 
	@PostMapping("/add")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{customerid}")
	public ResponseEntity<Customer> updateCustomerDetailsById(@Valid @RequestBody Customer customer,@PathVariable("customerid") Integer customerid) throws CustomerException{
		
		return new ResponseEntity<>(customerService.updateCustomer(customer, customerid),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{customerid}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerid") Integer customerid) throws CustomerException{
		
		return new ResponseEntity<>(customerService.deleteCustomer(customerid),HttpStatus.OK);
	}
	
	@GetMapping("/customers/getallcustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException{
		return new ResponseEntity<>(customerService.showAllCustomers(),HttpStatus.FOUND);
	}
	
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<Customer> showAllCustomerDetails(@PathVariable("customerid") Integer customerid) throws CustomerException{
		return new ResponseEntity<>(customerService.showCustomerDetailsById(customerid),HttpStatus.FOUND);
	}
	
	//product
	@GetMapping("/searchbyname")
	public ResponseEntity<List<Product>> searchProductByName(@RequestParam("productName") String productName)throws ProductException{
		return new ResponseEntity<List<Product>>(productService.searchByName(productName),HttpStatus.OK);
	}
	
	@GetMapping("/product/getallproduct")
	public ResponseEntity<List<Product>> allProduct(
	        @RequestParam(defaultValue = "0") int pageNumber,
	        @RequestParam(defaultValue = "50") int pageSize
	) throws ProductException {
	    return new ResponseEntity<>(productService.showAllProducts(pageNumber, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("/product/getallavailableproduct")
	public ResponseEntity<Page<Product>> allavailableProduct(
	        @RequestParam(defaultValue = "0") int pageNumber,
	        @RequestParam(defaultValue = "50") int pageSize
	) throws ProductException 
	{
	    return new ResponseEntity<Page<Product>>(productService.showAllProductsByAvailable(pageNumber, pageSize), HttpStatus.OK);
	}
	
	//category
	@GetMapping("/category")
	public ResponseEntity<List<Category>> showAllCategory()throws CategoryException{
		return new ResponseEntity<List<Category>>(categoryService.showAllCategory(),HttpStatus.OK);
	}
	
	//cart
	@PutMapping("/cart/addtocart/{customerId}/{productId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Integer customerId,@PathVariable Integer productId)throws CartsException{
		return new ResponseEntity<Cart>(cartService.addProductToCart(customerId, productId), HttpStatus.CREATED);
	}
	
	@PutMapping("/cart/removefromcart/{customerId}/{productId}")
	public ResponseEntity<Cart> removeProductToCart(@PathVariable Integer customerId,@PathVariable Integer productId)throws CartsException{
		return new ResponseEntity<Cart>(cartService.removeProductByCart(customerId, productId), HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/getfromcart/{customerId}")
	public ResponseEntity<List<Product>> showCartsProduct(@PathVariable Integer customerId){
		return new ResponseEntity<List<Product>>(cartService.getProductByCartId(customerId),HttpStatus.OK);
	}
	 @GetMapping("/getsortedandpaginatedproducts")
	    public ResponseEntity<List<Product>> getAllProductsWithSort(
	            @RequestParam(defaultValue = "0") int pageNumber,
	            @RequestParam(defaultValue = "50") int pageSize,
	            @RequestParam(defaultValue = "productName") String sortBy,
	            @RequestParam(defaultValue = "ASC") String sortDirection
	    ) throws ProductException {
	        List<Product> products = productService.showAllProductswithsort(pageNumber, pageSize, sortBy, sortDirection);
	        return ResponseEntity.ok(products);
	    }
	 
	 @GetMapping("/price/{orderid}")
		public ResponseEntity<String> totalPrice(@PathVariable("orderid") Integer orderid){
			return new ResponseEntity<>(sweetservice.calculateTotalCost(orderid),HttpStatus.OK);
		}
	 
	 @GetMapping("/getallorders/{customerId}")
		public ResponseEntity<List<SweetOrder>> showAllOrderOfCustomer(@PathVariable Integer customerId) throws CustomerException{
			return new ResponseEntity<List<SweetOrder>>(sweetservice.showAllOrderToCustomer(customerId),HttpStatus.OK);
		}
	 @GetMapping("/cart/{id}/product")
		public ResponseEntity<List<Product>> getProductByCustomerId(@PathVariable Integer id){
			return new ResponseEntity<List<Product>>(cartService.getProductByCartId(id),HttpStatus.OK);
		}
	
}
