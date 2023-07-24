package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Repository.CartRepo;
import com.sweetsdelight_bk.Repository.CustomerRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CartRepo cartrepo;
	
	@Override
    public Customer updateCustomerRole(Integer customerId, String role) throws CustomerException{
    	Optional<Customer> optional = customerRepo.findById(customerId);
    	
    	if(optional.isEmpty()) throw new CustomerException("User not found");
    	
    	Customer customer = optional.get();
    	
    	customer.setRole(role);
    	
    	return customerRepo.save(customer);
    }
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Optional<Customer> c=customerRepo.findByCustomerEmail(customer.getCustomerEmail());
		if(c.isPresent()) {
			throw new CustomerException("Email id  already used");
		}
		log.debug("Calling save method from CustomerJpa Repository");

		customer.setRole("USER");
		
		customer.setCustomerId(customer.getUserId());
		Cart cart=new Cart();
		cart.setGrandTotal(0.0);
		cart.setProductCount(0);
		cart.setTotal(0.0);
		cartrepo.save(cart);
		customer.setCart(cart);
		

		
		Customer cust=customerRepo.save(customer);
		
		Optional<Customer> cs= customerRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		
		cs.get().setCustomerId(cs.get().getUserId());
		
		log.info("Customer saved sucessfully");
		return cust;
		
	}

	@Override
	public Customer updateCustomer(Customer customer,Integer customerId) throws CustomerException {
		log.debug("Calling findById method from CustomerJpa Repository");
		Optional<Customer> opt=customerRepo.findById(customerId);
		Customer existingCustomer=opt.get();
		if(existingCustomer!=null){
			
			existingCustomer.setCustomerName(customer.getCustomerName());
			existingCustomer.setSweetOrders(customer.getSweetOrders());
			existingCustomer.setCart(customer.getCart());
			
			log.info("Customer name,its order and its carts updated Sucessfully");
			return existingCustomer;
			
		}else {
			throw new CustomerException("user with this customerId is not present");
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
			log.debug("Calling findById method from CustomerJpa Repository");
		   Optional<Customer> opt=customerRepo.findById(customerId);
			
			if(opt.isEmpty()) {
				throw new CustomerException("no Customer found with this id");
			}
			customerRepo.delete(opt.get());
			log.info("Customer deleted sucessfully");
			return opt.get();
	}

	@Override
	public List<Customer> showAllCustomers() throws CustomerException {
		
		log.debug("Calling findAll method from CustomerJpa Repository");
		List<Customer> customers=customerRepo.findAll();
		if(customers.isEmpty()) {
			throw new CustomerException("no customer present in database");
		}
		log.info("All Customer Got sucessfully");		
		return customers;
		
	}

	@Override
	public Customer showCustomerDetailsById(Integer userId) throws CustomerException {
		log.debug("Calling findById method from CustomerJpa Repository");
		Optional<Customer> opt=customerRepo.findById(userId);
		
		if(opt.isEmpty()) {
			throw new CustomerException("Customer does not exists");
		}
		log.info("Customer Got sucessfully");
		return opt.get();
	}



	
	

}
