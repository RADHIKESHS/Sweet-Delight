package com.sweetsdelight_bk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetsdelight_bk.Exceptions.CartsException;
import com.sweetsdelight_bk.Model.Cart;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Service.CartService;


@RestController
@RequestMapping("cart")
public class CartController {

	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<Cart> addCard(@RequestBody Cart cart)
	{
		Cart c=cartService.addCard(cart);
		
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Cart> updateCard(@RequestBody Cart cart)
	{
		Cart c=cartService.updateCard(cart);
		
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<String> deleteCard(@PathVariable("cartId") Integer cartId)
	{
		String c=cartService.DeleteCart(cartId);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/carts")
	public ResponseEntity<List<Cart>> allListOfCart(){
		
	       List<Cart> carts =cartService.showAllCarts();
		
		if(!carts.isEmpty())
		{
			return new ResponseEntity<>(carts,HttpStatus.OK);
		}
		else
		{
			throw new IllegalArgumentException("Carts List is Empty");
		}
	}
	
	@PutMapping("/{catId}/{prodId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Integer catId,@PathVariable Integer prodId)throws CartsException{
		return new ResponseEntity<Cart>(cartService.addProductToCart(catId, prodId), HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/{id}/product")
	public ResponseEntity<List<Product>> getProductByCustomerId(@PathVariable Integer id){
		return new ResponseEntity<List<Product>>(cartService.getProductByCartId(id),HttpStatus.OK);
	}
}
