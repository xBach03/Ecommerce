package com.springboot.ecommerce.dtos.address;

import com.springboot.ecommerce.models.User;

import java.time.LocalDate;

public record AddressReqDto(
        String country,

        String city,

        String street,

        LocalDate createdDate,

        User user
) {
}
