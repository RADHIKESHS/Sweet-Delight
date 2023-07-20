package com.sweetsdelight_bk.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String name;
	private String photoPath;
	private Double price;
	private String description;
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "category_Id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "admin_Id")
	private Admin admin;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<SweetItem> sweetItems;

	@ManyToOne
	@JoinColumn(name = "cart_Id")
	private Cart cart;

	public Product(String name, String photoPath, Double price, String description, boolean available) {
		super();
		this.name = name;
		this.photoPath = photoPath;
		this.price = price;
		this.description = description;
		this.available = available;
	}

}
