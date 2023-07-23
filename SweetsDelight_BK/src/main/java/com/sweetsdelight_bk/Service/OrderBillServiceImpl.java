package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.OrderBillException;
import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Repository.OrderBillRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderBillServiceImpl implements OrderBillService {

    @Autowired
    private OrderBillRepo billRepo;

   

    @Override
    public OrderBill updateOrderBill(OrderBill bill) {
    	log.debug("Calling findById method from OrderBillJpa repository");
        Optional<OrderBill> optional =  billRepo.findById(bill.getOrderBillId());
      if(optional.isEmpty()) throw new OrderBillException("order bill does not exits");
      	
		log.debug("Calling save method from OrderBillJpa repository");
      	OrderBill o= billRepo.save(bill);
		log.info("Bill updated successfully");
      return o;
    }

    @Override
    public OrderBill cancelOrderBill(Integer id) {

         Optional<OrderBill> optional = billRepo.findById(id);
         if(optional.isEmpty()) throw new OrderBillException("order bill does not exits");
 			log.debug("Calling delete method from OrderBillJpa repository");
 			
         	billRepo.deleteById(id);
 			log.info("Bill updated successfully");
        return optional.get();
    }

    @Override
    public List<OrderBill> showAllOrderBills() {
		log.debug("Calling findByAll method from OrderBillJpa repository");
        List<OrderBill> allOrderBill = billRepo.findAll();
        if(allOrderBill.isEmpty())throw new OrderBillException("No OrderBill exists");
		
        log.info("Bill got successfully");
        
        return allOrderBill;
        
    }

    @Override
    public OrderBill showOrderDetails(Integer id) {
        Optional<OrderBill> optional=billRepo.findById(id);
            return  optional.get();
    }
}
