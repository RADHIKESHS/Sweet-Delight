package com.sweetsdelight_bk.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product addProduct(Product product) {
		if(product==null)throw new SweetDelightBkException("Product is null");
		
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
	public List<Product> showAllProducts() {
		
		Pageable page=  PageRequest.of(0, 10, Sort.by("productId"));
		List<Product> list= productRepo.findAll(page).getContent();
		if(list.isEmpty())throw new SweetDelightBkException("No product are there");
		return list;
	}

	@Override
	public List<Product> showAllProductsById(Integer productId) {
		productRepo.findById(productId).orElseThrow(()->new SweetDelightBkException("No product found"));
		
		return productRepo.findByProductProductId(productId);
	}

}
