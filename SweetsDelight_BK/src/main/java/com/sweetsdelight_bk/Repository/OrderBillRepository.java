package com.sweetsdelight_bk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.OrderBill;

public interface OrderBillRepository extends JpaRepository<OrderBill, Integer>{
//	public List<Cart> findallbyBillid(Integer id);
}
