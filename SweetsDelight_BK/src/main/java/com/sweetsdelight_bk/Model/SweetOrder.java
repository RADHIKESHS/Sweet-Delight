package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SweetOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sweetOrderId;
	private LocalDateTime createdDate;
	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@OneToMany
	@JoinColumn(name = "sweetOrder")
	private List<SweetItem> sweetItems;

	private Map<Product, Long> groupedProducts;
	
	@ManyToOne
	@JoinColumn(name = "orderBill_Id")
	private OrderBill orderBill;
}
