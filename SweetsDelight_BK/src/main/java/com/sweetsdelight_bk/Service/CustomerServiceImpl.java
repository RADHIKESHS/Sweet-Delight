package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {



    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
    	if(customer==null) throw new SweetDelightBkException("customer value is null");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(int id,Customer customer) {
    	Optional< Customer> customerr=customerRepository.findById(id);
    	   if(customerr.isEmpty()) 
    		   throw new SweetDelightBkException("no Customer Availablable with this id");
    	       return customerRepository.save(customer);
    	    }

    @Override
    public Customer deleteCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
           // customer.setActive(false); // Assuming isActive is a boolean attribute to indicate customer's active status
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId + " not found.");
        }
    }

	@Override
	public List<Customer> showAllCustomers() {
	List<Customer> list=	customerRepository.findAll();
	if(list.isEmpty())throw new SweetDelightBkException("Now Customer available");
		return list;
	}

	@Override
	public Customer updateAccountInfo(int customerId, String newName, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer loginCustomer(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> displayAllSweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchSweet(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCart(int customerId, Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SweetOrder placeOrder(int customerId, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SweetOrder getOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SweetOrder> getAllOrders(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double generateOrderBill(int orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public List<Customer> showAllCustomers(int customerId) {
//	    
//        List<Customer> list= customerRepository.findAllbyCustomerid(customerId);
//        if(list.isEmpty())  throw new SweetDelightBkException("No item available with cartId "+customerId);
//        return list;
//	}

 
}
