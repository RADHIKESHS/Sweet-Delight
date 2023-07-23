package com.sweetsdelight_bk.Model;

 
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	private Integer productid;
	
	@NotNull(message = "Product name should not be null")
	@Size(min=3, max=40)
	private String name;
	
	@NotNull(message = "Photo is mandatory")
	private String photopath;
	
	@NotNull(message = "Price should be there")
	private Double price;
	
	@NotNull(message = "Please mention desription")
	private String description;
	
	@NotNull(message = "Confirm availability")
	private Boolean available;
	
	private int quantity;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private SweetOrder sweetOrder;
	
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;
	
    
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;


	@Override
	public String toString() {
		return "Product [productid=" + productid + ", name=" + name + ", photopath=" + photopath + ", price=" + price
				+ ", description=" + description + ", available=" + available + ", quantity=" + quantity + "]";
	}


	
	
	

	

	
	
	
	
}
