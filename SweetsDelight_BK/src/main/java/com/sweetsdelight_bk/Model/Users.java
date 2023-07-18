package com.sweetsdelight_bk.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Size(min = 5, max = 15, message = "Username should be greater than 5 and less than 15")
	private String username;
	@Size(min = 8, message = "Password should be greater than 8")
	private String password;
	private String confirmPassword;
	private String role;

	public Users(
			@Size(min = 5, max = 15, message = "Username should be greater than 5 and less than 15") String username,
			@Size(min = 8, message = "Password should be greater than 8") String password, String confirmPassword,
			String role) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}

}
