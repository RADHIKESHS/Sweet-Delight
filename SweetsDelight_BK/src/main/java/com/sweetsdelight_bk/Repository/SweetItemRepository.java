package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.SweetItem;

public interface SweetItemRepository extends JpaRepository<SweetItem, Integer> {

}
