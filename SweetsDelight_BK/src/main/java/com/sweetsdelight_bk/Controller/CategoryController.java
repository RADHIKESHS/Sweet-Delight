package com.sweetsdelight_bk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/add")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category){
		
		Category savedCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category) {
		
		Category updatedCategory = categoryService.updateCategory(category.getCategoryid(),category);
		
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCategoryHandler(@PathVariable("id") Integer id) {
		
		String result = categoryService.deleteCategory(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> showCategoryByIdHandler(@PathVariable("id") Integer id) {
		
		Category category = categoryService.showCategoryById(id);
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

}
