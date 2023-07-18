package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SweetOrder {

	private Integer sweetOrderId;
	private Users users;
	private List<SweetItem> sweetItem;
	private LocalDateTime createdDate;
	private Map<Product, Long> groupedProducts;

}
