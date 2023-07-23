package com.sweetsdelight_bk.Repository;
 


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.SweetOrder;

public interface SweetOrderRepo extends JpaRepository<SweetOrder, Integer>{
	List<SweetOrder> findByCustomer(Customer customer);
}
