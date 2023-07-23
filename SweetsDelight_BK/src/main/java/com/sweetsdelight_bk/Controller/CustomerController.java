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
@RequestMapping("/sweetDelight/customerUser")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private SweetOrderService sweetservice;

    @PostMapping("/register")   //user
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerId}")   //user
    public ResponseEntity<Customer> updateCustomerDetailsById(@Valid @RequestBody Customer customer,
                                                              @PathVariable("customerId") Integer customerId)
            throws CustomerException {
        return new ResponseEntity<>(customerService.updateCustomer(customer, customerId), HttpStatus.OK);
    }

    // Cart Actions

    @PutMapping("/carts/{customerId}/add/{productId}")   //user
    public ResponseEntity<Cart> addProductToCart(@PathVariable Integer customerId,
                                                 @PathVariable Integer productId) throws CartsException {
        return new ResponseEntity<>(cartService.addProductToCart(customerId, productId), HttpStatus.CREATED);
    }

    @PutMapping("/carts/{customerId}/remove/{productId}")   //user
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Integer customerId,
                                                      @PathVariable Integer productId) throws CartsException {
        return new ResponseEntity<>(cartService.removeProductByCart(customerId, productId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/carts/{customerId}")    //user
    public ResponseEntity<List<Product>> getCartProducts(@PathVariable Integer customerId) {
        return new ResponseEntity<>(cartService.getProductByCartId(customerId), HttpStatus.OK);
    }
    
	@GetMapping("/price/{orderid}")     //admin && user
		public ResponseEntity<String> totalPrice(@PathVariable("orderid") Integer orderid){
			return new ResponseEntity<>(sweetservice.calculateTotalCost(orderid),HttpStatus.OK);
	}
	
 	@GetMapping("/getallorders/{customerId}")    //admin && user
	public ResponseEntity<List<SweetOrder>> showAllOrderOfCustomer(@PathVariable Integer customerId) throws CustomerException{
		return new ResponseEntity<List<SweetOrder>>(sweetservice.showAllOrderToCustomer(customerId),HttpStatus.OK);
	}
 	
 	@GetMapping("/cart/{id}/product")     // admin && user
	public ResponseEntity<List<Product>> getProductByCustomerId(@PathVariable Integer id){
		return new ResponseEntity<List<Product>>(cartService.getProductByCartId(id),HttpStatus.OK);
	}
	 
}

