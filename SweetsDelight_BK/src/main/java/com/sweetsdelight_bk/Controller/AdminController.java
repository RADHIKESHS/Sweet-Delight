package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Service.CartService;

@RestController
@RequestMapping("/admin")
public class AdminController {


	    @Autowired
	    private CartService cartService;

	    @PostMapping
	    public Cart addCart(@RequestBody Cart cart) {
	        return cartService.addCart(cart);
	    }

	    @PutMapping("/{cartId}")
	    public Cart updateCart(@PathVariable int cartId, @RequestBody Cart cart) {
	        return cartService.updateCart(cart);
	    }

	    @DeleteMapping("/{cartId}")
	    public Cart cancelCart(@PathVariable int cartId) {
	        return cartService.cancelCart(cartId);
	    }

	    @GetMapping
	    public List<Cart> showAllCarts() {
	        return cartService.showAllcarts();
	    }

	    @GetMapping("/{cartId}")
	    public List<Cart> showAllCartsByCartId(@PathVariable int cartId) {
	        return cartService.showAllcarts(cartId);
	    }
	}

	
	
	
	
	
	
	
}
