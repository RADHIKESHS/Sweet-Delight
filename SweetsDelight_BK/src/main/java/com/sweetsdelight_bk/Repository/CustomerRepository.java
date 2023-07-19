package com.sweetsdelight_bk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findAllbyCustomerid(Long id);
}
