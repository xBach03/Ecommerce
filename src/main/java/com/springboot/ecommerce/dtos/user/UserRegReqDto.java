package com.springboot.ecommerce.dtos.user;

public record UserRegReqDto(
        String username,
        String password,
        String email
) {
}
