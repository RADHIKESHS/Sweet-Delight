package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.OrderBill;

public interface OrderBillService {
	 OrderBill addOrderBill(OrderBill orderBill);

	    OrderBill updateOrderBill(int id,OrderBill orderBill);

	    OrderBill cancelOrderBill(int orderBillId);

	    List<OrderBill> showAllOrderBills();

	    List<OrderBill> showAllOrderBills(int orderId);
}
