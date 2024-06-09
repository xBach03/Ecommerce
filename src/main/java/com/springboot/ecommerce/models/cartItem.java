package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class cartItem {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "cartId"
    )
    @JsonBackReference
    private cart cart;

    @OneToOne
    @JoinColumn(
            name = "productItemId"
    )
    private productItem productItem;

    @ManyToOne
    @JoinColumn(
            name = "storeId"
    )
    private store store;
}
