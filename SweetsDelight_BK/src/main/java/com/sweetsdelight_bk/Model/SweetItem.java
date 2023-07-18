package com.sweetsdelight_bk.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SweetItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sweetItemId;
	private Product product;

	@ManyToOne
	@JoinColumn(name = "sweetItem")
	private SweetOrder sweetOrder;

	@ManyToOne
	@JoinColumn(name = "sweetItem")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "sweetItem")
	private Admin admin;

}
