package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Model.Users;
import com.sweetsdelight_bk.Service.SweetOrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class SweetOrderController {

	private SweetOrderService sweetOrderService;

	@PostMapping("/sweetOrder")
	public ResponseEntity<SweetOrder> addSweetOrder(@Valid @RequestBody SweetOrder sweetOrder) {

		SweetOrder addSweetOrder = sweetOrderService.addSweetOrder(sweetOrder);
		return new ResponseEntity<SweetOrder>(addSweetOrder, HttpStatus.CREATED);
	}

	@PutMapping("/sweetOrder/sweetOrderId")
	public ResponseEntity<SweetOrder> updateSweetOrder(@PathVariable Integer sweetOrderId, SweetOrder sweetOrder) {

		SweetOrder updatedSweetOrder = sweetOrderService.updateSweetOrder(sweetOrderId, sweetOrder);
		return new ResponseEntity<SweetOrder>(updatedSweetOrder, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/sweetOrder/sweetOrderId")
	public ResponseEntity<String> cancelSweetOrder(@PathVariable Integer sweetOrderId) {

		sweetOrderService.cancelSweetOrder(sweetOrderId);
		return new ResponseEntity<>("Sweet Order Deleted Succesfully", HttpStatus.OK);
	}

	@GetMapping("/sweetOrder")
	public ResponseEntity<List<SweetOrder>> showAllSweetOrder() {
		List<SweetOrder> allOrders = sweetOrderService.showAllSweetOrder();
		return new ResponseEntity<List<SweetOrder>>(allOrders, HttpStatus.OK);
	}

}
