package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Repository.CustomerRepository;
import com.sweetsdelight_bk.Repository.OrderBillRepository;

public class OrderBillServiceImpl implements OrderBillService {
	
	@Autowired
	OrderBillRepository orderbillrepo;
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public OrderBill addOrderBill(OrderBill orderBill, int id) {
		if(orderBill==null)throw new SweetDelightBkException("order bill is null");
		Optional<Customer> cust= customerRepo.findById(id);
//		SweetOrder sweet=orderBill.getSweetOrders();
		
		
		if(cust.isEmpty())throw new SweetDelightBkException("Customer is not present of given id "+id);
		Customer customer= cust.get();
		List<OrderBill> list=customer.getOrderBill();
		list.add(orderBill);
		customer.setOrderBill(list);
		orderBill.setCustomer(customer);
		return orderbillrepo.save(orderBill);
	}

	@Override
	public OrderBill updateOrderBill(int id, OrderBill orderBill) {
		if(orderBill==null)throw new SweetDelightBkException("order bill is null");
		Optional<OrderBill> order=orderbillrepo.findById(id);
		if(order.isEmpty())throw new SweetDelightBkException("Order bill is not found");
		
		return orderbillrepo.save(order.get());
	}

	@Override
	public OrderBill cancelOrderBill(int orderBillId) {
		Optional<OrderBill> order=orderbillrepo.findById(orderBillId);
		if(order.isEmpty())throw new SweetDelightBkException("Order bill is not found");
		OrderBill temp= order.get();
		orderbillrepo.deleteById(orderBillId);
		return temp;
	}

	@Override
	public List<OrderBill> showAllOrderBills() {
		Pageable p= PageRequest.of(0, 5, Sort.by("orderBillId"));
		List<OrderBill> list= orderbillrepo.findAll(p).getContent();
		if(list.isEmpty())throw new SweetDelightBkException("No order bill is present");
		return list;
	}

	@Override
	public List<OrderBill> showAllOrderBillsofCustomerById(int customerId) {
		Optional<Customer> cust= customerRepo.findById(customerId);
		if(cust.isEmpty())throw new SweetDelightBkException("No customer is found of given id "+customerId);
		Customer customer=cust.get();
		List<OrderBill> list=customer.getOrderBill();
		if(list.isEmpty())throw new SweetDelightBkException("No order placed yet by the customer "+customer.getUsername());
		
		return list;
	}

	

}
