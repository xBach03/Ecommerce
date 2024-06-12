package com.springboot.ecommerce.dtos.cart;

import com.springboot.ecommerce.models.User;

import java.time.LocalDate;

public record CartDto(
        Double totalPrice,
        LocalDate createdDate,
        User user
) {
}
