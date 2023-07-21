package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.Customer;


public interface CustomerService {

    Customer addCustomer(Customer customer);

    Customer updateCustomer( int id,Customer customer);

    Customer cancelCustomer(int customerId);

    List<Customer> showAllCustomers();

//    List<Customer> showAllCustomers(int customerId);
}
