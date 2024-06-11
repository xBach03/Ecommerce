package com.springboot.ecommerce.dtos.user;

public record UserAuthReqDto(
        String email,
        String password
) {
}
