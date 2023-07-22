package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Exceptions.UserException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.User;

public interface AdminService {
	
	List<Customer> allCustomers() throws CustomerException;

    List<User> allUsers() throws UserException;


    List<Product> allProducts()throws ProductException;

    List<Category> allCategories() throws CategoryException;




}
