package com.sweetsdelight_bk.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Repository.CategoryRepository;
import com.sweetsdelight_bk.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Product addProduct(Product product,Integer categoryId) {
		if(product==null)throw new SweetDelightBkException("Product is null");
		Optional<Category> category= categoryRepo.findById(categoryId);
		if(category.isEmpty())throw new SweetDelightBkException("Given category id is not present");
		Category cate= category.get();
		List<Product> list= cate.getProducts();
		list.add(product);
		cate.setProducts(list);
		product.setCategory(cate);
		return productRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> prod= productRepo.findById(product.getProductId());
		if(prod.isEmpty())throw new SweetDelightBkException("No product found of product id "+product.getProductId());
		
		return productRepo.save(product);
	}

	@Override
	public Product cancelProduct(Integer productId) {
		Optional<Product> prod= productRepo.findById(productId);
		if(prod.isEmpty())throw new SweetDelightBkException("No product found of product id "+productId);
		Product temp= prod.get();
		productRepo.deleteById(productId);
		return temp;
	}

	@Override
	public List<Product> showAllProducts(int pageNumber,int pageSize) {
		
		Pageable page=  PageRequest.of(pageNumber, pageSize, Sort.by("productId"));
		List<Product> list= productRepo.findAll(page).getContent();
		if(list.isEmpty())throw new SweetDelightBkException("No product are there");
		return list;
	}

	@Override
	public List<Product> searchByName(String productName) {
		List<Product> list= productRepo.searchProductByName(productName.toLowerCase());
		if(list.isEmpty())throw new SweetDelightBkException("No product is found by search by name "+productName);
		return list;
	}

	//you can use for user
	@Override
	public Page<Product> showAllProductsByAvailable(int pageNumber,int pageSize) {
		PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
		Page<Product> page=productRepo.showAllAvailableProduct(pageRequest);
		if(page.isEmpty())throw new SweetDelightBkException("No product are there which are available");
		return page;
	}

	
	
	

}
