package com.sweetsdelight_bk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//	public List<Customer> findAllbyCustomerid(int id);
}
