package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.OrderBillException;
import com.sweetsdelight_bk.Model.OrderBill;

public interface OrderBillService {


    
     OrderBill   updateOrderBill(OrderBill bill) throws OrderBillException; // customer
     OrderBill cancelOrderBill(Integer id) throws OrderBillException;   //customer && admin

     List<OrderBill> showAllOrderBills() throws OrderBillException;    //admin

     OrderBill showOrderDetails(Integer id) throws OrderBillException;  //cutomer && admin


}
