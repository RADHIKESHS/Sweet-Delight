package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SweetOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sweetOrderId;
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@OneToMany(mappedBy = "sweetOrder", cascade = CascadeType.ALL)
	private List<SweetItem> sweetItems;

	@ElementCollection
	@CollectionTable(name = "sweet_order_products", joinColumns = @JoinColumn(name = "sweet_order_id"))
	@MapKeyJoinColumn(name = "product_id")
	@Column(name = "quantity")
	private Map<Product, Long> groupedProducts;

	@ManyToOne
	@JoinColumn(name = "orderBill_Id")
	private OrderBill orderBill;

	public SweetOrder(LocalDateTime createdDate) {
		super();
		this.createdDate = createdDate;
	}

}
