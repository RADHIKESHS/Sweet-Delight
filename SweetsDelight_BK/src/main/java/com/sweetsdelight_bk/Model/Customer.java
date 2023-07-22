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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends Users {

    public Customer(
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!^&*()])[A-Za-z\\d@#$%!^&*()]{8,}$", message = "Password must have at least 1 uppercase letter, 1 lowercase letter, 1 special character, 1 numeric character, and be at least 8 characters long") @Size(min = 5, max = 15, message = "Username should be greater than 5 and less than 15") String username,
			@Size(min = 8, message = "Password should be greater than 8") String password, String role) {
		super(username, password, role);
		// TODO Auto-generated constructor stub
	}

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
