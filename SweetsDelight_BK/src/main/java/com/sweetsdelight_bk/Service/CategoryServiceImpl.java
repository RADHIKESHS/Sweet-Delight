package com.sweetsdelight_bk.Service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository catrepo;
	
	@Override
	public Category addCategory(Category cat) {
		
		if(cat==null) throw new  SweetDelightBkException("Category  is null");
        return catrepo.save(cat);
	}

	@Override
	public Category updateCategory(int id,Category category) {
	     Optional< Category> cartt=catrepo.findById(id);
	     if(cartt.isEmpty()) 
		   throw new SweetDelightBkException("no category Availablable with this id");
	       return catrepo.save(category);
	    }

	@Override
	public Category deletecategory(int categoryid) {
		
		 Optional<Category> optionalCart = catrepo.findById(categoryid);
	        if(optionalCart.isEmpty()) throw new SweetDelightBkException("no cart availablable with this id");
	        	Category cart = optionalCart.get();
	            catrepo.deleteById(categoryid);
	            return cart;
	}


	@Override
	 public Page<Category> getPaginatedCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return catrepo.findAll(pageable);
    }



	@Override
	public Page<Product> showAllItemsInCategory(int categoryId, int pageNumber, int pageSize) {
	    Optional<Category> optionalCategory = catrepo.findById(categoryId);
	    if (optionalCategory.isEmpty()) 
	        throw new SweetDelightBkException("No category available with this id");
	    Category category = optionalCategory.get();
	    List<Product> products = category.getProducts();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize);
	    Page<Product> productPage = new PageImpl<>(products, pageable, products.size());
	    return productPage;
	}


	
}
