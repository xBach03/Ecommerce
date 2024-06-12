package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.dtos.cartitem.CartItemDto;
import com.springboot.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCartId(Integer id);
}
