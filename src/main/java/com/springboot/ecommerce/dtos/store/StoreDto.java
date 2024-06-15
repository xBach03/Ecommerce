package com.springboot.ecommerce.dtos.store;

import java.time.LocalDate;

public record StoreDto(
        String name,
        String location,
        String rate,
        LocalDate joiningDate
) {
}
