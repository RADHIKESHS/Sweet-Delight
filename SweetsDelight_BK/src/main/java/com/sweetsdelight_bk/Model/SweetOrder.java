package com.sweetsdelight_bk.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SweetOrder {
	public enum OrderStatus {
	    PENDING,
	    PROCESSING,
	    SHIPPED,
	    DELIVERED,
	    ON_HOLD,
	    BACKORDERED,
	    CANCELLED,
	    RETURNED,
	    REFUNDED,
	    COMPLETED
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sweetOrderId;



    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sweetOrder")
    private List<Product> products = new ArrayList<>();
    
    @OneToOne
    private OrderBill orderBill;
    
    @NotNull
    private LocalDateTime date;
    
    @NotNull
    private OrderStatus status = OrderStatus.PENDING;

    @Override
    public String toString() {
        return "SweetOrder{" +
                "sweetOrderId=" + sweetOrderId +
                ", customer=" + customer +
                ", date=" + date +
                '}';
    }
}

