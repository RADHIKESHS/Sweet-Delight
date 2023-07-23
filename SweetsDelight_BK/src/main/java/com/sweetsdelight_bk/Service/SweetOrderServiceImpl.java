package com.sweetsdelight_bk.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Exceptions.OrderException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Customer;
import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Repository.CartRepo;
import com.sweetsdelight_bk.Repository.CustomerRepo;
import com.sweetsdelight_bk.Repository.OrderBillRepo;
import com.sweetsdelight_bk.Repository.SweetOrderRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class SweetOrderServiceImpl  implements SweetOrderService {

	@Autowired
	private SweetOrderRepo orderRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CustomerRepo customerrepo;
	
	@Autowired
    private OrderBillRepo billRepo;
	
	@Override
	public String placeOrder(int customerid) throws CustomerException {
        Customer cus=customerrepo.findById(customerid).orElseThrow(()->new CustomerException("No customer available"));
		log.debug("Calling save method from SweetJpa Repository");
		Cart cart=cus.getCart();
		List<Product> list=cart.getListProduct();
		List<Product> ans=new ArrayList<>();
		for(Product i: list) {
			if(i.getQuantity()>=1) {
				ans.add(i);
			}
		}
		
		SweetOrder order=new SweetOrder();
		order.setCustomer(cus);
		order.setDate(LocalDateTime.now());
		order.setProducts(ans);
		OrderBill bill= new OrderBill();
		bill.setCustomer(cus);
		bill.setOrderBill(LocalDate.now());
		bill.setTotalCost(cart.getTotal());
		bill.setSweetOrderList(order);
		billRepo.save(bill);
		cart.setListProduct(new ArrayList<>());
		cart.setTotal(0.0);
		cart.setProductCount(0);
		order.setOrderBill(bill);
		orderRepo.save(order);
		log.info("Order saved sucessfully");
		return "order saved successfully";
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
	
	

	@Override
	public List<SweetOrder> showAllOrderToCustomer(Integer customerId) throws CustomerException {
		Customer customer=customerrepo.findById(customerId).orElseThrow(()-> new CustomerException("Customer not found"));
		List<SweetOrder>list= orderRepo.findByCustomer(customer);
		for(SweetOrder i: list) {
			System.out.println(i.getProducts());
		}
		return orderRepo.findByCustomer(customer);
	}
}
