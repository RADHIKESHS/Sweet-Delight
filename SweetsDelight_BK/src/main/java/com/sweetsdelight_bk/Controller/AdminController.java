package com.sweetsdelight_bk.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Exceptions.UserException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Repository.UserRepo;
import com.sweetsdelight_bk.Service.AdminService;
import com.sweetsdelight_bk.Service.CartService;
import com.sweetsdelight_bk.Service.CategoryService;
import com.sweetsdelight_bk.Service.CustomerService;
import com.sweetsdelight_bk.Service.OrderBillService;
import com.sweetsdelight_bk.Service.ProductService;
import com.sweetsdelight_bk.Service.SweetOrderService;
import com.sweetsdelight_bk.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sweetDelight/admin")
public class AdminController {


    @Autowired
     private AdminService adminService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CartService cartser;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderBillService orderbillservice;
    
    @Autowired
 	private SweetOrderService service;
    
    @Autowired
 	private UserService userservice;
    
	@Autowired
	private UserRepo cRepo;
	
	@Autowired
	private PasswordEncoder pc ;
    
    
    
    @PostMapping("/register")   //user
    public ResponseEntity<User> registerCustomer(@Valid @RequestBody User admin) throws CustomerException {
    	admin.setPassword(pc.encode(admin.getPassword()));
        return new ResponseEntity<>(userservice.saveuser(admin), HttpStatus.CREATED);
    }
    
    
    @GetMapping("/users")  //admin
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users =adminService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.FOUND);

    }


    @GetMapping("/customers")   //admin
    public ResponseEntity<List<Customer>> getAllCustomer() throws CustomerException {

        List<Customer> customers =adminService.allCustomers();
        return new ResponseEntity<>(customers, HttpStatus.FOUND);

    }
    
    @GetMapping("/customers/{customerId}")      // admin
	public ResponseEntity<Customer> showCustomerDetails(@PathVariable("customerid") Integer customerid) throws CustomerException{
		return new ResponseEntity<>(customerService.showCustomerDetailsById(customerid),HttpStatus.FOUND);
	}

    
    @PostMapping("/products/{categoryId}")    // admin 
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product,@PathVariable Integer categoryId) throws ProductException {
		
		Product savedProduct = productService.addProduct(product,categoryId);
		
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

    @PutMapping("/product/update/{productId}")   //admin
	public ResponseEntity<Product> updateProductHandler(@PathVariable("productId") int productId,@RequestBody Product product) throws ProductException {
		
		Product updatedProduct = productService.updateProduct(productId,product);
		
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
    
    @DeleteMapping("/products/{productId}")   //admin
	public ResponseEntity<String> deleteProductHandler(@PathVariable("productId") Integer productId) throws ProductException {
		
		String result = productService.deleteProduct(productId);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
    @GetMapping("/products/{productId}")       //admin
	public ResponseEntity<Product> showProductById(@PathVariable("productId") Integer productId) throws ProductException {
		
		Product product = productService.showProductById(productId);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	
    @PutMapping("/products/{prodId}/categories/{catId}")    //admin 
	public ResponseEntity<Product> addProductToCategory(@PathVariable Integer prodId,@PathVariable Integer catId)throws ProductException,CategoryException{
		return new ResponseEntity<Product>(productService.addProductToCategory(prodId, catId), HttpStatus.CREATED);
	}
	
	
    @PostMapping("/category")    // admin
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category){
		
		Category savedCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	
    @PutMapping("/categories/update/{categoryId}")  //admin
	public ResponseEntity<Category> updateCategoryHandler(@PathVariable("categoryId") int categoryId ,@RequestBody Category category) {
		
		Category updatedCategory = categoryService.updateCategory(categoryId,category);
		
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	
    @DeleteMapping("/categories/{categoryId}") //admin
	public ResponseEntity<String> deleteCategoryHandler(@PathVariable("id") Integer id) {
		
		String result = categoryService.deleteCategory(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    
    @GetMapping("/allcarts")   // admin
	public ResponseEntity<List<Cart>> allListOfCart(){
		
	    List<Cart> carts =cartser.showAllCarts();		
		if(!carts.isEmpty()){
			return new ResponseEntity<>(carts,HttpStatus.OK);
		}else{
			throw new IllegalArgumentException("Carts List is Empty");
		}
	}
    
    @GetMapping("/bills/allbills")       //  admin 
    public ResponseEntity<List<OrderBill>> showAllOrdersbill(){
        return new ResponseEntity<>(orderbillservice.showAllOrderBills(),HttpStatus.OK);

    }
    
    @GetMapping("/orderbill/{customerbillid}")    // admin   
    public ResponseEntity<OrderBill> totalPrice(@PathVariable("customerbillid") Integer customerbillid){
        return new ResponseEntity<>(orderbillservice.showOrderDetails(customerbillid),HttpStatus.OK);
    }
    
    @GetMapping("/orders/allorders")      //admin 
	public ResponseEntity<List<SweetOrder>> showAllOrders(){
		return new ResponseEntity<>(service.showAllOrders(),HttpStatus.OK);

	}
    
	@GetMapping("/logini")
	public ResponseEntity<String> logInUserHandler(Authentication auth){
		 Optional<User> opt= cRepo.findByUsername(auth.getName());
		 if(opt.isEmpty()) throw new UserException("No user found") ;
		 User user = opt.get();
		 return new ResponseEntity<>(user.getUsername()+" Logged In Successfully", HttpStatus.ACCEPTED);
	}

}
