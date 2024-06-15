package com.springboot.ecommerce.dtos.user;

public record UserRegDto(
        String username,
        String password,
        String email
) {
}
