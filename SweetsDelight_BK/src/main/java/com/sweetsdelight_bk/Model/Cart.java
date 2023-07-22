package com.sweetsdelight_bk.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
	private Double grandTotal;

	@JsonIgnore
	@OneToMany(mappedBy = "cart",cascade = CascadeType.PERSIST)
	private List<Product> listProduct=new ArrayList<>();
	
	private Integer productCount=this.listProduct.size();
	private Double total;

}
