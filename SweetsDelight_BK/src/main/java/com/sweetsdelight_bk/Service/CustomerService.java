package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;


public interface CustomerService {
	 // Customer operations
    Customer addCustomer(Customer customer);

    Customer updateCustomer(int id, Customer customer);

    Customer updateAccountInfo(int customerId, String newName, String email);
    
    Customer deleteCustomer(int customerId);

    List<Customer> showAllCustomers();   ///admin

    // Customer account operations
    Customer loginCustomer(String username, String password);

    // Sweet operations
    List<Product> displayAllSweets();

    List<Product> searchSweet(String keyword);

    // Cart and Order operations
    void addToCart(int customerId, Product product);

    SweetOrder placeOrder(int customerId, Cart cart);

    SweetOrder getOrderDetails(int orderId);

    List<SweetOrder> getAllOrders(int customerId);

    double generateOrderBill(int orderId);
}
