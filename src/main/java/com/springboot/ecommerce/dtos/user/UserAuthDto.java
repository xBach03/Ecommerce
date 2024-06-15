package com.springboot.ecommerce.dtos.user;

public record UserAuthDto(
        String email,
        String password
) {
}
