package com.springboot.ecommerce.dtos.orderitem;

import com.springboot.ecommerce.models.Order;
import com.springboot.ecommerce.models.ProductItem;

public record OrderItemDto(
        Integer quantity,
        Double price,
        Order oder,
        ProductItem productItem
) {
}
