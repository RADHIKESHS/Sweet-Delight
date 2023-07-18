package com.sweetsdelight_bk.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!^&*()])[A-Za-z\\d@#$%!^&*()]{8,}$",
			message = "Password must have at least 1 uppercase letter, 1 lowercase letter, 1 special character, "
					+ "1 numeric character, and be at least 8 characters long")
	@Size(min = 5, max = 15, message = "Username should be greater than 5 and less than 15")
	private String username;

	@Size(min = 8, message = "Password should be greater than 8")
	private String password;
	private String confirmPassword;
	private String role;

	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private Admin admin;
}
