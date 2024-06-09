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
public class voucher {
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
    private product product;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference
    private user user;
}
