package com.sweetsdelight_bk.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sweetsdelight_bk.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
//	List<Product> findByName(String name);
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:productName%")
	List<Product> searchProductByName(String productName);
	
	@Query("SELECT p FROM Product p WHERE p.available = true")
	Page<Product> showAllAvailableProduct(Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.name = :category")
	Page<Product> showAllProductByCategory(String category, Pageable pageable);
}
