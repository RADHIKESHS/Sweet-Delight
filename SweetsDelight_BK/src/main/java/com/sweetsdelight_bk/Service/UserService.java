package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.User;



public interface UserService{
	
	public User saveuser(User u1);
	public User UpdateUser(User users);
	public String  CancelUser(Integer userId);
	public List<User> ShowAllUser();
}
