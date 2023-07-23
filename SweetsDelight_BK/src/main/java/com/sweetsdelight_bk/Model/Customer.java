package com.sweetsdelight_bk.Model;
import java.util.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Customer extends User{

    private Integer customerId;

    @NotNull
    @Size(min = 4, max = 30, message = "min length should be 4 and max should be 30")
    private String customerName;
	
    @Column(unique = true)
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email not be blank")
	private String customerEmail;
	
	@NotBlank(message = "address neded")
	private String Address;
    

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<SweetOrder> sweetOrders = new HashSet<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<OrderBill> bills = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + customerId +
                ", Customername='" + customerName + '\'' +
                '}';
    }
}

