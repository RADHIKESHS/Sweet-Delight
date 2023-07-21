package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderBillId;

    private LocalDateTime localDate;

    private float totalCost;

    @OneToOne(mappedBy = "orderBill", cascade = CascadeType.ALL)
    private SweetOrder sweetOrders;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OrderBill(LocalDateTime localDate, float totalCost) {
        super();
        this.localDate = localDate;
        this.totalCost = totalCost;
    }
}
