package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.cartitem.CartItemDto;
import com.springboot.ecommerce.models.CartItem;
import com.springboot.ecommerce.repositories.CartItemRepository;
import com.springboot.ecommerce.services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItemDto>> findAll() {
        List<CartItemDto> cartItemList = cartItemService.findAll();
        if(cartItemList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

    @GetMapping("/cart-items/{cart-id}")
    public ResponseEntity<List<CartItemDto>> findAllByUserId(@PathVariable("cart-id") Integer id) {
        List<CartItemDto> cartItemList = cartItemService.findAllByUserId(id);
        if(cartItemList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

    @PostMapping("/cart-items")
    public ResponseEntity<CartItemDto> save(@RequestBody CartItem cartItem) {
        CartItemDto savedItem = cartItemService.save(cartItem);
        return savedItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-items/delete/{cart-id}")
    public ResponseEntity<CartItemDto> delete(@PathVariable("cart-id") Integer id) {
        CartItemDto deletedCartItem = cartItemService.delete(id);
        return deletedCartItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedCartItem, HttpStatus.OK);
    }
}
