package com.sweetsdelight_bk.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	private Integer productId;
	private String name;
	private String photoPath;
	private Double price;
	private String description;
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "category_Id")
	private Category category;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Admin admin;

	@OneToMany(mappedBy = "sweetItem_Id", cascade = CascadeType.ALL)
	private List<SweetItem> sweetItems;
	
	@ManyToOne
	@JoinColumn(name = "product")
	private Cart cart;
}
