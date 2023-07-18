package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
