package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Repository.OrderBillRepository;

public class OrderBillServiceImpl implements OrderBillService {
	
	@Autowired
	OrderBillRepository orderbillrepo;

	@Override
	public OrderBill addOrderBill(OrderBill orderBill) {
		if(orderBill==null) throw new SweetDelightBkException("orderBill value is null");
        return orderbillrepo.save(orderBill);
	}

	@Override
	public OrderBill updateOrderBill( int id,OrderBill orderBill) {
		Optional< OrderBill> customerr=orderbillrepo.findById(id);
 	   if(customerr.isEmpty()) 
 		   throw new SweetDelightBkException("no Bill Availablable with this id");
 	       return orderbillrepo.save(orderBill);
	}

	@Override
	public OrderBill cancelOrderBill(int orderBillId) {
		 Optional<OrderBill> optionalbill = orderbillrepo.findById( orderBillId);
	        if (optionalbill.isPresent()) {
	            OrderBill customer = optionalbill.get();
	           // customer.setActive(false); // Assuming isActive is a boolean attribute to indicate customer's active status
	            return orderbillrepo.save(customer);
	        } else {
	            throw new IllegalArgumentException("Customer with ID " + orderBillId + " not found.");
	        }
	}

	@Override
	public List<OrderBill> showAllOrderBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderBill> showAllOrderBills(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
