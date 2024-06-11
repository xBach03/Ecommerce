package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
