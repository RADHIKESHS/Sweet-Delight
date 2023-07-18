package com.sweetsdelight_bk.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	private String password;

	@OneToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "user_Id")
	private Users user;

	@OneToMany
	@JoinColumn(name = "sweetItem_Id")
	private List<SweetItem> sweetItem;

	@OneToOne
	@JoinColumn(name = "cart_Id")
	private Cart cart;

	@OneToOne
	@JoinColumn(name = "product_Id")
	private Product product;

	@OneToOne
	@JoinColumn(name = "category_Id")
	private Category category;
}
