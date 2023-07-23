package com.sweetsdelight_bk.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderBillId;

    @NotNull(message = "Date should be there")
    private LocalDate orderBill;
    
    @NotNull(message = "Total should not be null")
    private Double totalCost;

    @JsonIgnore
    @OneToOne(mappedBy = "orderBill", cascade = CascadeType.ALL)
    private SweetOrder sweetOrderList;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @Override
    public String toString() {
        return "OrderBill{" +
                "orderBillId=" + orderBillId +
                ", orderBill=" + orderBill +
                ", totalCost=" + totalCost +
                '}';
    }
}
