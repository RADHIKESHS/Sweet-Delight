package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.OrderException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Repository.CartRepo;
import com.sweetsdelight_bk.Repository.SweetOrderRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class SweetOrderServiceImpl  implements SweetOrderService {

	@Autowired
	private SweetOrderRepo orderRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public String addSweetOrder(SweetOrder order) {

		if(order==null)throw new OrderException("sweetOrder should not be null");
		log.debug("Calling save method from SweetJpa Repository");
			orderRepo.save(order);
		log.info("Order saved sucessfully");
		return "order saved successfull";
	}

	@Override
	public SweetOrder updateSweetOrder(SweetOrder order) {
		// TODO Auto-generated method stub
		log.debug("Calling findbyId method from SweetJpa repository");
		Optional<SweetOrder> optional=orderRepo.findById(order.getSweetOrderId());
	      if(optional.isEmpty()) throw new OrderException("this sweetorder does not exists");
	      SweetOrder s=	orderRepo.save(order);
	      log.info("Order updated sucessfully");

	      return s;
	}

	@Override
	public String cancelSweetOrder(Integer orderId) throws OrderException {
		// TODO Auto-generated method stub
		log.debug("Calling findbyId method from SweetJpa repository");
		Optional<SweetOrder> optional=orderRepo.findById(orderId);
		if(optional.isPresent()) {
			orderRepo.deleteById(orderId);
		     log.info("Order updated sucessfully");
			return "Product deleted successfully";
		}
		
		else throw new OrderException("with this id no order found");
		 
	}

	@Override
	public List<SweetOrder> showAllOrders() {
		log.debug("Calling findbyId method from SweetJpa repository");
		List<SweetOrder> o= orderRepo.findAll();
		if(o.isEmpty())throw new OrderException("No order exists");
		log.info("all order got");
		return o;
	}

	@Override
	public String calculateTotalCost(Integer orderId) throws OrderException {
		// TODO Auto-generated method stub
		Optional<SweetOrder> optional=orderRepo.findById(orderId);
		SweetOrder order=optional.get();
		Optional<Cart> cr= cartRepo.findById(order.getCustomer().getUserId());
		
		List<Product> products=order.getProducts();
		 Double total=0.0;


		for(int i=0;i<products.size();i++) {
			total+=products.get(i).getPrice();
		}
		 
		cr.get().setGrandTotal(total);
		cr.get().setTotal(total);
		return "Total price of this order is "+total;
	}

}
