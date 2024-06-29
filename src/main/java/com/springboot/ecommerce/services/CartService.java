package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.cart.CartDto;
import com.springboot.ecommerce.models.Cart;
import com.springboot.ecommerce.repositories.CartRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDto toCartDto(Cart cart) {
        return new CartDto(
                cart.getTotalPrice(),
                cart.getCreatedDate(),
                cart.getUser()
        );
    }

    public List<CartDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return cartRepository.findAll(pageable).stream()
                .map(this::toCartDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findByUser(Integer userId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return cartRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(this::toCartDto)
                .collect(Collectors.toList());
    }

    public CartDto save(Cart cart) {
        cart.setCreatedDate(LocalDate.now());
        return toCartDto(cartRepository.save(cart));
    }

    public CartDto delete(Integer id) {
        CartDto deletedCart = cartRepository.findById(id).map(this::toCartDto).orElse(null);
        cartRepository.deleteById(id);
        return deletedCart;
    }
}

