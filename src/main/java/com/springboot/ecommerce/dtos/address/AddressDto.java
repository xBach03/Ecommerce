package com.springboot.ecommerce.dtos.address;

import java.time.LocalDate;

public record AddressDto(

        String country,

        String city,

        String street,

        LocalDate createdDate
) {
}
