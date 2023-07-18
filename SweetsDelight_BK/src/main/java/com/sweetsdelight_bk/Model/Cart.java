package com.sweetsdelight_bk.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	private Integer productCount;
	private double total;
	private double grandTotal;

	@OneToMany(mappedBy = "product_Id")
	private List<Product> product;

	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
	private Admin admin;
}
