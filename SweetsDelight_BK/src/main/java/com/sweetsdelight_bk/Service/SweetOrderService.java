package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.OrderException;
import com.sweetsdelight_bk.Model.SweetOrder;

public interface SweetOrderService {

	public String placeOrder(int customer) throws CustomerException;
	
	public SweetOrder updateSweetOrder(SweetOrder order);
	
	
	public String cancelSweetOrder(Integer orderId) throws OrderException;
	
	public List<SweetOrder>  showAllOrders();
	
	public String calculateTotalCost(Integer orderId) throws OrderException;
	
}
