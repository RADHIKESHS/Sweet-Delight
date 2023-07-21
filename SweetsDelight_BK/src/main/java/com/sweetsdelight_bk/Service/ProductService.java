package com.sweetsdelight_bk.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetsdelight_bk.Model.Product;

public interface ProductService {
	public Product addProduct(Product product,Integer categoryId);
	public Product updateProduct(Product product);
	public Product cancelProduct(Integer productId);
	public List<Product> searchByName(String productName);
	public List<Product> showAllProducts(int pageNumber,int pageSize);
	public Page<Product> showAllProductsByAvailable(int pageNumber,int pageSize);

}
