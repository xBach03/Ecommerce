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
    @JsonBackReference(
            value = "cart-cartItem"
    )
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
    @JsonBackReference(
            value = "store-cartItem"
    )
    private Store store;

    public CartItem(Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }
}
