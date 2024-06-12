package com.springboot.ecommerce.dtos.cartitem;

import com.springboot.ecommerce.models.Cart;
import com.springboot.ecommerce.models.ProductItem;
import com.springboot.ecommerce.models.Store;

public record CartItemDto(
    Integer quantity,
    Double price,
    Cart cart,
    ProductItem productItem,
    Store store
) {
}
