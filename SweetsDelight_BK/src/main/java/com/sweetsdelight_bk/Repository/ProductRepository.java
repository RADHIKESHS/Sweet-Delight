package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
