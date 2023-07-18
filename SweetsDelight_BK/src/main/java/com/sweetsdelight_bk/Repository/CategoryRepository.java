package com.sweetsdelight_bk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
