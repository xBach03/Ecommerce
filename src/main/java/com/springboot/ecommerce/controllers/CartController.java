package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.cart.CartDto;
import com.springboot.ecommerce.models.Cart;
import com.springboot.ecommerce.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDto>> findAll() {
        List<CartDto> cartList = cartService.findAll();
        if(cartList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @GetMapping("/carts/{user-id}")
    public ResponseEntity<List<CartDto>> findByUserId(@PathVariable("user-id") Integer userId) {
        List<CartDto> cartList = cartService.findByUser(userId);
        if(cartList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("/carts")
    public ResponseEntity<CartDto> save(@RequestBody Cart cart) {
        CartDto savedCart = cartService.save(cart);
        return savedCart == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedCart, HttpStatus.OK);
    }

    @DeleteMapping("/carts/delete/{cart-id}")
    public ResponseEntity<CartDto> delete(@PathVariable("cart-id") Integer id) {
        CartDto deletedCart = cartService.delete(id);
        return deletedCart == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedCart, HttpStatus.OK);
    }

}
