package com.sweetsdelight_bk.Model;

import java.util.List;

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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	private Integer productCount;
	private double total;
	private double grandTotal;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<Product> products;

	@OneToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@OneToOne
    @JoinColumn(name = "admin_Id")
    private Admin admin;

	public Cart(Integer productCount, double total, double grandTotal) {
		super();
		this.productCount = productCount;
		this.total = total;
		this.grandTotal = grandTotal;
	}

}
