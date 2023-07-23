package com.sweetsdelight_bk.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
}
