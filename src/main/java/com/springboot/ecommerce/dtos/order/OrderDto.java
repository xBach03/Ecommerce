package com.springboot.ecommerce.dtos.order;

import java.time.LocalDate;

public record OrderDto(
        LocalDate createdDate,
        Double totalPrice,
        String paymentInfo,
        String status
) {
}
