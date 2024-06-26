package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.cartitem.CartItemDto;
import com.springboot.ecommerce.models.CartItem;
import com.springboot.ecommerce.repositories.CartItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItemDto toCartItemDto(CartItem cartItem) {
        return new CartItemDto(
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getCart(),
                cartItem.getProductItem(),
                cartItem.getStore()
        );
    }

    public List<CartItemDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return cartItemRepository.findAll(pageable).stream()
                .map(this::toCartItemDto)
                .collect(Collectors.toList());
    }

    public List<CartItemDto> findAllByUserId(Integer userId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return cartItemRepository.findAllByCartId(userId, pageable).stream()
                .map(this::toCartItemDto)
                .collect(Collectors.toList());
    }

    public CartItemDto save(CartItem cartItem) {
        return toCartItemDto(cartItemRepository.save(cartItem));
    }

    public CartItemDto delete(Integer id) {
        CartItemDto deletedCartItem = cartItemRepository.findById(id).map(this::toCartItemDto).orElse(null);
        cartItemRepository.deleteById(id);
        return deletedCartItem;
    }
}
