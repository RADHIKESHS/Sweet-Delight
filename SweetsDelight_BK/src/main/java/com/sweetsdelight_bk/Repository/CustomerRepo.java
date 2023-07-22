package com.sweetsdelight_bk.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByCustomerEmail(String customerEmail);
}
