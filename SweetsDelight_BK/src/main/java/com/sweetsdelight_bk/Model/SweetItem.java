package com.sweetsdelight_bk.Model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class SweetItem {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer sweetItemId;
//
//	@ManyToOne
//	@JoinColumn(name = "product_Id")
//	private Product product;
//
//	@ManyToOne
//	@JoinColumn(name = "sweetOrder_Id")
//	private SweetOrder sweetOrder;
//
//	@ManyToOne
//	@JoinColumn(name = "customer_Id")
//	private Customer customer;
//
//
//	public SweetItem(Product product) {
//		super();
//		this.product = product;
//	}
//
//}
