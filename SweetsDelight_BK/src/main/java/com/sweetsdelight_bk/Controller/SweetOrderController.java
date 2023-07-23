package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.CustomerException;
import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Service.SweetOrderService;


@RestController
@RequestMapping("/sweetorder")
public class SweetOrderController {
	
	
	@Autowired
	private SweetOrderService service;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<String>  saveSweetOrder(@PathVariable Integer id) throws CustomerException{
		  
		return new ResponseEntity<>(service.placeOrder(id),HttpStatus.CREATED);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id){
		return new ResponseEntity<>(service.cancelSweetOrder(id),HttpStatus.OK);

	}
	
	
	@PutMapping("/update")
	public ResponseEntity<SweetOrder> updateSweetOrder(@RequestBody SweetOrder order){
		return new ResponseEntity<>(service.updateSweetOrder(order),HttpStatus.OK);

	}
	
	
	@GetMapping("/orders")
	public ResponseEntity<List<SweetOrder>> showAllOrders(){
		return new ResponseEntity<>(service.showAllOrders(),HttpStatus.OK);

	}
	
	@GetMapping("/price/{id}")
	public ResponseEntity<String> totalPrice(@PathVariable("id") Integer id){
		return new ResponseEntity<>(service.calculateTotalCost(id),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<SweetOrder>> showAllOrderOfCustomer(@PathVariable Integer customerId) throws CustomerException{
		return new ResponseEntity<List<SweetOrder>>(service.showAllOrderToCustomer(customerId),HttpStatus.OK);
	}
	
	

}
