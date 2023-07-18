package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderBillId;
	private LocalDateTime localDate;
	private float totalCost;
	private List<SweetOrder> sweetOrders;

}
