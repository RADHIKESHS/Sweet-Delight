package com.sweetsdelight_bk.Repository;


 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetsdelight_bk.Model.OrderBill;

public interface OrderBillRepo extends JpaRepository<OrderBill,Integer> {

}
