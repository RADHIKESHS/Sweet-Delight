package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Repository.SweetOrderRepository;

@Service
public class SweetOrderServiceImpl implements SweetOrderService {

	@Autowired
	private SweetOrderRepository sweetOrderRepo;

	@Override
	public SweetOrder addSweetOrder(SweetOrder sweetOrder) {
		if (sweetOrder.getCreatedDate() == null || sweetOrder.getOrderstatus() == null
				|| sweetOrder.getCustomer() == null) {
			throw new IllegalArgumentException(
					"Invalid SweetOrder. Created date, order status, and customer must be provided.");
		}
		return sweetOrderRepo.save(sweetOrder);
	}

	@Override
	public SweetOrder updateSweetOrder(Integer sweetOrderId, SweetOrder sweetOrder) {
		Optional<SweetOrder> existingUser = sweetOrderRepo.findById(sweetOrderId);
		if (existingUser.isEmpty()) {
			throw new SweetDelightBkException("SweetOrder with ID " + sweetOrderId + " not found.");
		}

		SweetOrder orderPresent = existingUser.get();
		return sweetOrderRepo.save(orderPresent);
	}

	@Override
	public void cancelSweetOrder(Integer sweetOrderId) {
		Optional<SweetOrder> existingUser = sweetOrderRepo.findById(sweetOrderId);
		if (!existingUser.isPresent()) {
			throw new SweetDelightBkException("Order with ID " + sweetOrderId + " not found.");
		}

		sweetOrderRepo.deleteById(sweetOrderId);
	}

	@Override
	public List<SweetOrder> showAllSweetOrder() {
		List<SweetOrder> allOrder = sweetOrderRepo.findAll();
		if (allOrder.isEmpty()) {
			throw new SweetDelightBkException("No Order found");
		}
		return allOrder;
	}

	@Override
	public double totalCost(Integer sweetOrderId) {
//		Optional<SweetOrder> sweetOrderPresent = sweetOrderRepo.findById(sweetOrderId);
//	    if (sweetOrderPresent.isEmpty()) {
//	        throw new RuntimeException("SweetOrder with ID " + sweetOrderId + " not found.");
//	    }

		return (Double) null;
	}

}
