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

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Service.AdminService;
import com.sweetsdelight_bk.Service.CategoryService;
import com.sweetsdelight_bk.Service.ProductService;
import com.sweetsdelight_bk.Service.SweetOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
     private AdminService adminService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
	private SweetOrderService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users =adminService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.FOUND);

    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer() throws CustomerException {

        List<Customer> customers =adminService.allCustomers();
        return new ResponseEntity<>(customers, HttpStatus.FOUND);

    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws ProductException {

        List<Product> products =adminService.allProducts();
        return new ResponseEntity<>(products, HttpStatus.FOUND);

    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories =adminService.allCategories();
        return new ResponseEntity<>(categories, HttpStatus.FOUND);

    }
    
    @PostMapping("/product/add/{id}")
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product,@PathVariable Integer id) throws ProductException {
		
		Product savedProduct = productService.addProduct(product,id);
		
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

    @PutMapping("/product/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product) throws ProductException {
		
		Product updatedProduct = productService.updateProduct(product);
		
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
    
    @DeleteMapping("/product/delete/{productId}")
	public ResponseEntity<String> deleteProductHandler(@PathVariable("productId") Integer productId) throws ProductException {
		
		String result = productService.deleteProduct(productId);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> showProductById(@PathVariable("productId") Integer productId) throws ProductException {
		
		Product product = productService.showProductById(productId);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	
	@PutMapping("/assign_product_category/{prodId}/{catId}")
	public ResponseEntity<Product> addProductToCategory(@PathVariable Integer prodId,@PathVariable Integer catId)throws ProductException,CategoryException{
		return new ResponseEntity<Product>(productService.addProductToCategory(prodId, catId), HttpStatus.CREATED);
	}
	
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
	///Category
	
	@PostMapping("/category/add")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category){
		
		Category savedCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/category/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category) {
		
		Category updatedCategory = categoryService.updateCategory(category);
		
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/category/delete/{id}")
	public ResponseEntity<String> deleteCategoryHandler(@PathVariable("id") Integer id) {
		
		String result = categoryService.deleteCategory(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> showCategoryByIdHandler(@PathVariable("id") Integer id) {
		
		Category category = categoryService.showCategoryById(id);
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	///sweetorders
	
	@GetMapping("/orders")
	public ResponseEntity<List<SweetOrder>> showAllOrders(){
		return new ResponseEntity<>(service.showAllOrders(),HttpStatus.OK);

	}
}
