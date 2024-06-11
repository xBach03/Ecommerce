package com.springboot.ecommerce.dtos.product;

import java.time.LocalDate;

public record ProductDto(
        String name,
        String description,
        String rate,
        LocalDate createdDate
) {
}
