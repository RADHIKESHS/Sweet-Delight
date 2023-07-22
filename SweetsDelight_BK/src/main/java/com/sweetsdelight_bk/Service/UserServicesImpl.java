package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.UserException;
import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Repository.UserRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServicesImpl implements UserService {

	@Autowired
	private UserRepo userRepo;


	
	@Override
	public User saveuser(User u1) {
		if(u1==null)throw new UserException("user should not be null");
		log.debug("Calling save method from UserJpa Repository");
		User u2= userRepo.save(u1);
		log.info("user saved sucessfully");
		return u2;
	}

	@Override
	public User UpdateUser(User users) {
		log.debug("Calling findbyid method from UserJpa Repository");
		 Optional<User> opt= userRepo.findById(users.getUserId());
		 
		 if(opt.isPresent()){
			log.debug("Calling save method from UserJpa Repository");
			 log.info("user Updated sucessfully");
			return userRepo.save(users);
		 }else{
			 throw new UserException("user not exist");
		 }
	}

	@Override
	public String CancelUser(Integer userId) {
		log.debug("Calling findbyid method from UserJpa Repository");
		Optional<User> opt=userRepo.findById(userId);
		if(opt.isPresent()){
			log.debug("Calling delete method from UserJpa Repository");
			userRepo.deleteById(userId);
			log.info("user deleted sucessfully");
			return "User Deleted";
		}else{
			throw new UserException("user not Exist");
		}
		
		
	}

	@Override
	public List<User> ShowAllUser() {
		List<User> allUser=userRepo.findAll();
		if(!allUser.isEmpty()){
			return allUser;
		}else{
			throw new UserException("user Not Found");
		}
		
	}

	

}
