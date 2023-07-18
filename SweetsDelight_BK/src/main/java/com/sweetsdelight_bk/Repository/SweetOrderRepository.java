package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.SweetOrder;

public interface SweetOrderRepository extends JpaRepository<SweetOrder, Integer> {

}
