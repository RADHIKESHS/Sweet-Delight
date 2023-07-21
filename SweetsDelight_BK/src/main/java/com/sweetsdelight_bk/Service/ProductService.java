package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public Product cancelProduct(Integer productId);
	public List<Product> showAllProducts();
//	public List<Product> showAllProductsById(Integer productId);
}
