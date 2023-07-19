package com.sweetsdelight_bk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sweetsdelight_bk.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,PagingAndSortingRepository<Product,Integer> {
	public List<Product> showAllProductsById(Integer productId);
	List<Product> findByProductProductId(Integer productId);
	
}
