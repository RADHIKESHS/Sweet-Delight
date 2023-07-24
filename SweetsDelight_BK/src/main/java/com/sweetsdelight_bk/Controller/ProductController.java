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
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Service.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/add/{Categoryid}")
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product,@PathVariable Integer Categoryid) throws ProductException {
		
		Product savedProduct = productService.addProduct(product,Categoryid);
		
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product) throws ProductException {
		
		Product updatedProduct = productService.updateProduct(product.getProductid(),product);
		
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProductHandler(@PathVariable("productId") Integer productId) throws ProductException {
		
		String result = productService.deleteProduct(productId);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> showProductById(@PathVariable("productId") Integer productId) throws ProductException {
		
		Product product = productService.showProductById(productId);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
//	@GetMapping("/products")
//	public ResponseEntity<List<Product>> showAllProducts() throws ProductException {
//		
//		List<Product> products = productService.showAllProduct();
//		
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}
	
	@PutMapping("/{prodId}/{catId}")
	public ResponseEntity<Product> addProductToCategory(@PathVariable Integer prodId,@PathVariable Integer catId)throws ProductException,CategoryException{
		return new ResponseEntity<Product>(productService.addProductToCategory(prodId, catId), HttpStatus.CREATED);
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
	
	
    @GetMapping("/getsortedandpaginated")
    public ResponseEntity<List<Product>> getAllProductsWithSort(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) throws ProductException {
        List<Product> products = productService.showAllProductswithsort(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/product/getallavailableproduct/{category}")
    public ResponseEntity<Page<Product>> getAllProductsByCategory(
    		@PathVariable String category,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "50") int pageSize
    ) throws ProductException {
    	return new ResponseEntity<Page<Product>>(productService.showAllProductByCategory(category, pageNumber, pageSize), HttpStatus.OK);
    }
}
