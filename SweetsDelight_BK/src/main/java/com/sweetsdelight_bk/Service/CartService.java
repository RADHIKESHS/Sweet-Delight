package com.sweetsdelight_bk.Service;

import java.util.List;

import com.sweetsdelight_bk.Exceptions.CartsException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Product;


public interface CartService {
	public Cart addCard(Cart cart);
	public Cart updateCard(Cart cart);
	public String DeleteCart(Integer cartId);
	public List<Cart> showAllCarts();
	public Cart addProductToCart(Integer cartId,Integer productId)throws CartsException;
	public List<Product> getProductByCartId(Integer customerId);
	public Cart removeProductByCart(Integer customerId,Integer productId);
}
