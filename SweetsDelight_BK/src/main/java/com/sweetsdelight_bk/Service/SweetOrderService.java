package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.SweetOrder;

public interface SweetOrderService {

	public SweetOrder addSweetOrder(SweetOrder sweetOrder);

	public SweetOrder updateSweetOrder(Integer sweetOrderId, SweetOrder sweetOrder);

	public void cancelSweetOrder(Integer sweetOrderId);

	public List<SweetOrder> showAllSweetOrder();

	public double totalCost(Integer sweetOrderId);
}
