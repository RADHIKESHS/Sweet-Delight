package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Model.Customer;

public interface CustomerService {
	
    public Customer addCustomer(Customer customer) throws CustomerException; //customer
	
	public Customer updateCustomer(Customer customer,Integer customerId) throws CustomerException;   ///customer
	
	public Customer deleteCustomer(Integer userId) throws CustomerException;  //admin & customer
	
	public List<Customer> showAllCustomers() throws CustomerException;   /// admin
	
	public Customer showCustomerDetailsById(Integer userId) throws CustomerException;  //admin
	
	public Customer updateCustomerRole(Integer customerId, String role) throws CustomerException;
}
