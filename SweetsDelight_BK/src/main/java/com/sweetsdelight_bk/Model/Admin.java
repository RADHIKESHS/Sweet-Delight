package com.sweetsdelight_bk.Model;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
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
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!^&*()])[A-Za-z\\d@#$%!^&*()]{8,}$",
			message = "Password must have at least 1 uppercase letter, 1 lowercase letter, 1 special character, "
					+ "1 numeric character, and be at least 8 characters long")
	private String password;

	@OneToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@Nullable
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
