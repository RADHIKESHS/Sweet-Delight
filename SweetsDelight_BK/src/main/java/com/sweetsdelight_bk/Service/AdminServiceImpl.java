package com.sweetsdelight_bk.Service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Exceptions.UserException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Repository.CategoryRepo;
import com.sweetsdelight_bk.Repository.CustomerRepo;
import com.sweetsdelight_bk.Repository.ProductRepo;
import com.sweetsdelight_bk.Repository.UserRepo;

@Service
public class AdminServiceImpl implements AdminService {



	@Autowired(required = true)
	private CustomerRepo customerDao;


	@Autowired(required = true)
	private UserRepo userRepository;

	@Autowired(required = true)
	private ProductRepo productRepository;

	@Autowired(required = true)
	private CategoryRepo categoryRepository;

	@Override
	public List<Customer> allCustomers() throws CustomerException {
		List<Customer> customers= customerDao.findAll();
		if(customers.isEmpty()) throw  new CustomerException("no customer exists");
		else return customers;
	}

	@Override
	public List<User> allUsers() throws UserException {
		List<User> Users =userRepository.findAll();
		if(Users.isEmpty()) throw  new UserException("no user exists");
		else return Users;
	}

	@Override
	public List<Product> allProducts() throws ProductException {
		List<Product> products=productRepository.findAll();
		if(products.isEmpty()) throw  new ProductException("no product exists");
		else return products;
	}

	@Override
	public List<Category> allCategories() throws CategoryException {
		List<Category> categories=categoryRepository.findAll();
		if(categories.isEmpty()) throw  new CategoryException("no categories exists");
		else return categories;
	}


}
