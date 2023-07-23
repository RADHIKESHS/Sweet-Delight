package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.CartsException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Repository.CartRepo;
import com.sweetsdelight_bk.Repository.CustomerRepo;
import com.sweetsdelight_bk.Repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
	
	
	
	@Autowired
	private CartRepo cartRepositoty;
	
	@Autowired
	private ProductRepo productRepository;
	
	@Autowired
	private CustomerRepo customerRepository;
	@Override
	public Cart addCard(Cart cart) {
		if(cart==null)throw new CartsException("Cart shuld not be null");
		log.debug("Calling Save method from CartJpa Repository");
		Cart SavedCart=cartRepositoty.save(cart);
		log.info("Cart saved successfully");
		return SavedCart;
	}

	@Override
	public Cart updateCard(Cart cart) {
		Optional<Cart> opt=cartRepositoty.findById(cart.getCartId());
		
		if(opt.isPresent()){
			log.debug("Calling Save method from CartJpa Repository"); 		
			Cart upCart=cartRepositoty.save(cart);
			log.info("Cart updated successfully");
			return upCart;
		}
		else{
			 throw new CartsException("Cart Doen't Exist");
		}
	}

	@Override
	public String DeleteCart(Integer userId) {
		Optional<Cart> opt=cartRepositoty.findById(userId);
		if(opt.isPresent()){
			log.debug("Calling Save method from CartJpa Repository"); 	
			cartRepositoty.deleteById(userId);
			log.info("Cart deleted successfully");
			return "User Deleted";
		}
		else
		{
			throw new CartsException("user not Exist");
		}
	}

	@Override
	public List<Cart> showAllCarts() {
		log.debug("Calling findAll method from CartJpa Repository"); 
		List<Cart> allList=cartRepositoty.findAll();
		if(allList.isEmpty())throw new CartsException("No carts available");
		
		log.info("Getting all cart");
		return allList;
	}

	@Override
	public Cart addProductToCart(Integer customerId, Integer productId)throws CartsException {
		log.debug("Calling findById method from CartJpa Repository"); 
		Optional<Customer> opt=customerRepository.findById(customerId);
		if(opt.isEmpty())throw new CartsException("No cart found");
		Customer cust= opt.get();
		Cart cart=cust.getCart();
		Product product= productRepository.findById(productId).orElseThrow(()-> new CartsException("No proudct found"));
		List<Product> 	list= cart.getProducts();
		list.add(product);
		cart.setProducts(list);
		cart.setProductCount(list.size());
		

		List<Double> total= list.stream().map(Product::getPrice).toList();
		Double d=0.0;
		for(Double i:total) {
			d+=i;
		}
		cart.setTotal(d);
		productRepository.save(product);
    return cartRepositoty.save(cart);
	}
	
	@Override
	public List<Product> getProductByCartId(Integer customerId){
		Optional<Customer> opt=customerRepository.findById(customerId);
		if(opt.isEmpty())throw new CartsException("No customer found");
		Customer cust= opt.get();
		Cart cart=cust.getCart();
		

	List<Product> list= cart.getProducts();
		return list;
	}

	@Override
	public Cart removeProductByCart(Integer customerId, Integer productId) {
	    Optional<Customer> opt = customerRepository.findById(customerId);
	    if (opt.isEmpty()) {
	        throw new CartsException("No cart found");
	    }

	    Customer cust = opt.get();
	    Cart cart = cust.getCart();
	    List<Product> list = cart.getProducts();

	    // Remove the product with the given productId from the list
	    List<Product> updatedList = list.stream()
	                                    .filter(product -> !product.getProductid().equals(productId))
	                                    .collect(Collectors.toList());

	    // Update the cart with the new product list and calculate the total price
	    double total = 0.0;
	    for (Product product : updatedList) {
	        total += product.getPrice();
	    }
	    cart.setProducts(updatedList);
	    cart.setProductCount(updatedList.size());
	    cart.setTotal(total);

	    // Save the updated cart to the database
	    return cartRepositoty.save(cart);

	
	}

	
	
	
	
	
	
}
