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
public class CartItem {
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
    private Cart cart;

    @OneToOne
    @JoinColumn(
            name = "productItemId"
    )
    private ProductItem productItem;

    @ManyToOne
    @JoinColumn(
            name = "storeId"
    )
    private Store store;
}
