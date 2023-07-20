package com.sweetsdelight_bk.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetsdelight_bk.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
public List<Cart> findAllByCartId(Integer id);
}
