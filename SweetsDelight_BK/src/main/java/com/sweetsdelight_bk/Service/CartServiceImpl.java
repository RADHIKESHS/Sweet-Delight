package com.sweetsdelight_bk.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl {

    @Autowired
    private CartRepository cartRepository;

    public Cart addCart(Cart cart) {
    	if(cart==null) throw new  SweetDelightBkException("Cart is null");
        return cartRepository.save(cart);
    }

   public Cart updateCart(int id,Cart cart)
   {
     Optional< Cart> cartt=cartRepository.findById(id);
     if(cartt.isEmpty()) 
	   throw new SweetDelightBkException("no cart Availablable with this id");
       return cartRepository.save(cart);
    }

    public Cart cancelCart(int cartId) 
    {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if(optionalCart.isEmpty()) throw new SweetDelightBkException("no cart availablable with this id");
        	Cart cart = optionalCart.get();
            cartRepository.deleteById(cartId);
            return cart;
    }

  

    public Page<Cart> showAllCarts(int page,int size) {
      
           Pageable pageable = PageRequest.of(page, size);
           return cartRepository.findAll(pageable);
       }
    }


