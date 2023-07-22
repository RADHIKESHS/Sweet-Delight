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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServices;

	@PostMapping("/add")
	public ResponseEntity<User> saveUser(@RequestBody User u1)
	{
		 User u4= userServices.saveuser(u1);
		 
		 return new ResponseEntity<>(u4,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<User> UpdateUser(@RequestBody User u){
		User updateduser =userServices.UpdateUser(u);
		
		return new ResponseEntity<>(updateduser,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> DeleteUser(@PathVariable("userId") Integer userId )
	{
		String deletedUser=userServices.CancelUser(userId);
		 
		 return new ResponseEntity<>(deletedUser,HttpStatus.OK);
	}
	@GetMapping("/users")
	public ResponseEntity<List<User>> alluser(){
		List<User> allUser=userServices.ShowAllUser();
		return new ResponseEntity<>(allUser,HttpStatus.ACCEPTED);
	}
}
