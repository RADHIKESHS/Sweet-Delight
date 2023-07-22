package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.Users;
import com.sweetsdelight_bk.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) {
		Users users = userService.addUser(user);
		return new ResponseEntity<Users>(users, HttpStatus.CREATED);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<Users> updateUser(@PathVariable Long userId, @Valid @RequestBody Users user) {
		Users updatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<Users>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> allUsers = userService.showAllusers();
		return new ResponseEntity<List<Users>>(allUsers, HttpStatus.OK);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> cancelUser(@PathVariable Long userId) {
		userService.cancelUser(userId);
		return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
		Users user = userService.findUserById(userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

}
