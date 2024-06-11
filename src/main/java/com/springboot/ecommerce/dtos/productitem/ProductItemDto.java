package com.springboot.ecommerce.dtos.productitem;

public record ProductItemDto(
        String color,
        String size,
        Integer soldQuantity,
        Integer currentQuantity,
        Double price
) {

}
