package com.sweetsdelight_bk.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
