package com.springboot.ecommerce.dtos.voucher;

import java.time.LocalDate;

public record VoucherDto(
        String name,
        String description,
        String discountAmount,
        LocalDate createdDate,
        LocalDate validDate

) {
}
