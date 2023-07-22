package com.sweetsdelight_bk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.MyErrorDetails;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Service.CartService;
import com.sweetsdelight_bk.Service.CartServiceImpl;
import com.sweetsdelight_bk.Service.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SwiggyController {
	
	@Autowired
	CartServiceImpl cartser;
	
	@Autowired
	CategoryServiceImpl catser;
	
	
	@PostMapping("/cart/add")
	public ResponseEntity<Cart> addnewcart(@RequestBody Cart cart){
		Cart c=cartser.addCart(cart);
		return new ResponseEntity<>(c,HttpStatus.CREATED);
	}
	
	@RequestMapping("/cart/showpaginated")
	 public ResponseEntity<Page<Category>> getPaginatedCart(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "4") int size
	    ) {
	        Page<Category> categoryPage = catser.getPaginatedCategories(page, size);
	        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
	    }
	
	@PostMapping("/category/add")
	public ResponseEntity<Category> addnewcat(@RequestBody Category category){
		
		Category cat=catser.addCategory(category);
		return new ResponseEntity<>(cat,HttpStatus.CREATED);
	}
	
	@RequestMapping("/category/showallpaginated")
	 public ResponseEntity<Page<Category>> getPaginatedCategories(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "4") int size
	    ) {
	        Page<Category> categoryPage = catser.getPaginatedCategories(page, size);
	        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
	    }
	

//	@PostMapping("/category/delete/{categoryId}")
//	public ResponseEntity<Category> addnewcat(@PathVariable int categoryId){
//		
//		Category cat=catser.deletecategory(categoryId);
//		return new ResponseEntity<>(cat,HttpStatus.CREATED);
//	}
	
}
