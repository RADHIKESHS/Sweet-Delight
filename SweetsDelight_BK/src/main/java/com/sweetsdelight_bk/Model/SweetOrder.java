package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SweetOrder {

	private Integer sweetOrderId;
	private LocalDateTime createdDate;
	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "sweetItem_Id")
	private List<SweetItem> sweetItem;

	private Map<Product, Long> groupedProducts;
	@ManyToOne
	@JoinColumn(name = "orderBill_Id")
	private OrderBill orderBill;
}
