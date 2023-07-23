package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.OrderBill;
import com.sweetsdelight_bk.Service.OrderBillService;

@RestController
@RequestMapping("/orderbill")
public class OrderBillController {

    @Autowired
    private OrderBillService service;


   


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderBill> deleteOrderBill(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.cancelOrderBill(id),HttpStatus.OK);

    }


    @GetMapping("/update")
    public ResponseEntity<OrderBill> updateOrderBill(@RequestBody OrderBill orderBill){
        return new ResponseEntity<>(service.updateOrderBill(orderBill),HttpStatus.OK);

    }


    @GetMapping("/bills")
    public ResponseEntity<List<OrderBill>> showAllOrders(){
        return new ResponseEntity<>(service.showAllOrderBills(),HttpStatus.OK);

    }

    @GetMapping("/orderbill/{id}")
    public ResponseEntity<OrderBill> totalPrice(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.showOrderDetails(id),HttpStatus.OK);
    }




}
