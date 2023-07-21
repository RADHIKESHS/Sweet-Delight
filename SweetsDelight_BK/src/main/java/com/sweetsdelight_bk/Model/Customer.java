package com.sweetsdelight_bk.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<SweetOrder> sweetOrder = new HashSet();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderBill> orderBill = new ArrayList<>();

    @OneToOne(mappedBy = "customer")
    private Cart cart;
}
