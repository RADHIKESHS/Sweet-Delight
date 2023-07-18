package com.sweetsdelight_bk.Model;

import jakarta.persistence.Entity;
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
	private Category category;
}
