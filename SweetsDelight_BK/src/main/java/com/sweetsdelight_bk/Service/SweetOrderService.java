package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.OrderException;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;

public interface SweetOrderService {

	public String placeOrder(int customer) throws CustomerException;
	
	public SweetOrder updateSweetOrder(SweetOrder order);
	
	public SweetOrder updateSweetOrderStatus(Integer orderId, SweetOrder.OrderStatus status) throws OrderException;
	
	public String cancelSweetOrder(Integer orderId) throws OrderException;
	
	public List<SweetOrder>  showAllOrders();
	
	public String calculateTotalCost(Integer orderId) throws OrderException;
	
	public List<SweetOrder> showAllOrderToCustomer(Integer customerId) throws CustomerException;
	
}
