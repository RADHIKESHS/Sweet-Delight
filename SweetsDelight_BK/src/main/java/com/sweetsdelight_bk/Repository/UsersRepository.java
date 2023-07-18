package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
