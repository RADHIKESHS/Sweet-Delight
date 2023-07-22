package com.sweetsdelight_bk.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.SweetOrder;
import com.sweetsdelight_bk.Model.Users;

public interface SweetOrderRepository extends JpaRepository<SweetOrder, Integer> {

}
