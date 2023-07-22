package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.Cart;


public interface CartService {
	public Cart addCard(Cart cart);
	public Cart updateCard(Cart cart);
	public String DeleteCart(Integer cartId);
	public List<Cart> showAllCarts();
}
