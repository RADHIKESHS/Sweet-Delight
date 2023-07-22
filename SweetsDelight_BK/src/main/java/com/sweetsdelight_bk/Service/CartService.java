package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Product;

public interface CartService {
	 public Cart addCart(Cart cart);
	 public Cart updateCart(Cart cart);
	 public Cart cancelCart(int cartId);
	 public List<Cart> showAllcarts();
	 public List<Product> showAllitemsincart(int cartid);	      	    
}
