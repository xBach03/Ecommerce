package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    private String discountAmount;

    private LocalDate createdDate;

    private LocalDate validDate;

    @OneToOne
    @JoinColumn(
            name = "productId"
    )
    @JsonBackReference(
            value = "product-voucher"
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference(
            value = "user-voucher"
    )
    private User user;

    public Voucher(String name, String description, String discountAmount, LocalDate createdDate, LocalDate validDate) {
        this.name = name;
        this.description = description;
        this.discountAmount = discountAmount;
        this.createdDate = createdDate;
        this.validDate = validDate;
    }

    public Voucher(String name, String description, String discountAmount, LocalDate validDate) {
        this.name = name;
        this.description = description;
        this.discountAmount = discountAmount;
        this.validDate = validDate;
    }
}
