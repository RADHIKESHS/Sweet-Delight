package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Repository.CategoryRepo;

import lombok.extern.slf4j.Slf4j;


 
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public Category addCategory(Category category) throws CategoryException {
		if(category==null)throw new CategoryException("category should not null");
		log.debug("Calling save method from CategoryJpa repository");
		Category savedCategory = categoryRepo.save(category);
		log.info("Category added successfully");
		
		return savedCategory;
	}

	@Override
	public Category updateCategory(int categoryId,  Category category) throws CategoryException {
		Optional<Category> opt = categoryRepo.findById(categoryId);
		
		if(opt.isPresent()) {
			log.debug("Calling save method from CategoryJpa repository");
			Category updatedCategory = categoryRepo.save(category);
			log.info("Category updated successfully");
			return updatedCategory;
		} else {
			throw new CategoryException("Category not updated!");
		}
	}

	@Override
	public String deleteCategory(Integer categoryId) throws CategoryException {
		
		Optional<Category> opt = categoryRepo.findById(categoryId);
		
		if(opt.isPresent()) {
			log.debug("Calling delete method from CategoryJpa repository");
			categoryRepo.delete(opt.get());
			log.info("Category deleted successfully");
			return "Category deleted successfully.";
		} else {
			throw new CategoryException("Category not delete!");
		}
	}

	@Override
	public Category showCategoryById(Integer categoryId) throws CategoryException {
		log.debug("Calling findbyId method from CategoryJpa repository");
		Optional<Category> opt = categoryRepo.findById(categoryId);
		
		if(opt.isPresent()) {
			Category c= opt.get();
			log.info("Category find and get successfully");
			return c;
		} else {
			throw new CategoryException("Category not found!");
		}
	}

	@Override
	public List<Category> showAllCategory() throws CategoryException {
		// TODO Auto-generated method stub
		log.debug("Calling findbyId method from CategoryJpa repository");
		List<Category> getcat = categoryRepo.findAll();
		if(getcat.isEmpty())throw new CategoryException("No any category available now");
		
		log.info("AllCategory find and get successfully");
		return getcat;
	}

}
