package com.springboot.ecommerce.dtos.user;

import java.time.LocalDate;

public record UserDto(
        String password,
        String phoneNumber,
        String email,
        String gender,
        LocalDate createdDate
) {
}
