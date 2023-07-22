package com.sweetsdelight_bk.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
//public Page<Category> findAll(Pageable page);

}
