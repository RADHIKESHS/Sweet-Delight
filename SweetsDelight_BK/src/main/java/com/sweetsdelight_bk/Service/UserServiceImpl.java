package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Users;
import com.sweetsdelight_bk.Repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Users addUser(Users users) throws SweetDelightBkException {
		if (usersRepository.findByUsername(users.getUsername()).isPresent()) {
			throw new SweetDelightBkException("Username is alreday present with " + users.getUsername() + " username");
		}
		return usersRepository.save(users);
	}

	@Override
	public Users updateUser(Long userId, Users users) throws SweetDelightBkException {

		Optional<Users> exitingUser = usersRepository.findById(userId);
		if (!exitingUser.isPresent()) {
			throw new SweetDelightBkException("User not found with " + userId + " id");
		}
		Users userPresent = exitingUser.get();
		return usersRepository.save(userPresent);
	}

	@Override
	public void cancelUser(Long userId) throws SweetDelightBkException {
		Optional<Users> existingUser = usersRepository.findById(userId);
		if (!existingUser.isPresent()) {
			throw new SweetDelightBkException("User with ID " + userId + " not found.");
		}

		usersRepository.deleteById(userId);

	}

	@Override
	public List<Users> showAllusers() throws SweetDelightBkException {
		List<Users> allUser = usersRepository.findAll();
		if (allUser.isEmpty()) {
			throw new SweetDelightBkException("No user found");
		}
		return allUser;

	}

	@Override
	public Users findUserById(Long userId) throws SweetDelightBkException {
		Optional<Users> existingUser = usersRepository.findById(userId);
		if (!existingUser.isPresent()) {
			throw new SweetDelightBkException("User with ID " + userId + " not found.");
		}
		Users userPresent = existingUser.get();
		return userPresent;
	}

}
