package com.sweetsdelight_bk.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
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
    
    @Enumerated
    private OrderStatus orderstatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private List<Product> sweetproduct = new ArrayList<>();
//    error get

    @ManyToOne
    @JoinColumn(name = "orderBill_id")
    private OrderBill orderBill;
}
