package com.sweetsdelight_bk.Model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	private String username;

	@OneToMany(mappedBy = "sweetOrder_Id", cascade = CascadeType.ALL)
	private Set<SweetOrder> sweetOrder;

	@OneToMany(mappedBy = "sweetItem_Id", cascade = CascadeType.ALL)
	private List<SweetItem> sweetItems;
	
	@OneToOne
	@JoinColumn(name = "cart_Id")
	private Cart cart;
	
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private Admin admin;
}
