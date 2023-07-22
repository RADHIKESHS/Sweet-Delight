package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Users;

public interface UserService {

	public Users addUser(Users users) throws SweetDelightBkException;

	public Users updateUser(Long userId, Users users) throws SweetDelightBkException;

	public void cancelUser(Long userId) throws SweetDelightBkException;

	public List<Users> showAllusers() throws SweetDelightBkException;

	public Users findUserById(Long userId) throws SweetDelightBkException;
}
