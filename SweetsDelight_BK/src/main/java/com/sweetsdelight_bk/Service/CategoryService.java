package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Model.Category;

public interface CategoryService {

	public Category addCategory(Category category) throws CategoryException;
	
	public String deleteCategory(Integer categoryId) throws CategoryException;

	public Category showCategoryById(Integer categoryId) throws CategoryException;
	
	public List<Category> showAllCategory() throws CategoryException;

	Category updateCategory(int categoryId, Category category) throws CategoryException;
}
