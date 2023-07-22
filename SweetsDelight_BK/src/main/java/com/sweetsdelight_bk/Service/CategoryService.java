package com.sweetsdelight_bk.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Product;

public interface CategoryService {
	 public Category addCategory(Category category);
	 public Category updateCategory(int id,Category category);
	 public Category deletecategory(int category);
	 public Page<Category> getPaginatedCategories(int page,int size);
	 public Page<Product> showAllItemsInCategory(int category,int PageNumber,int pageSize);
}
