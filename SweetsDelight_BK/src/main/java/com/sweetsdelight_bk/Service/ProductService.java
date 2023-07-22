package com.sweetsdelight_bk.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Product;



public interface ProductService {
	
	public Product addProduct(Product product,Integer categoryId) throws ProductException;//admin
	
	public Product updateProduct(Product product) throws ProductException;//admin
	
	public String deleteProduct(Integer productId) throws ProductException;//admin
	
	public Product showProductById(Integer productId) throws ProductException;//admin
	
	public List<Product> showAllProduct() throws ProductException;// admin & user
	
	public List<Product> searchByName(String productName) throws ProductException;    // user 
	
	public List<Product> showAllProducts(int pageNumber,int pageSize) throws ProductException;  // admin & user
	
	public Page<Product> showAllProductsByAvailable(int pageNumber,int pageSize) throws ProductException; // admin // user
	
	public Product addProductToCategory(Integer productId,Integer categoryId)throws ProductException,CategoryException;
	
}
