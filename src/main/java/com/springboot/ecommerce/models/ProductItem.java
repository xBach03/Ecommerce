package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem {
    @Id
    @GeneratedValue
    private Integer id;

    private String color;

    private String size;

    private Integer soldQuantity;

    private Integer currentQuantity;

    private Double price;

    @OneToOne(
            mappedBy = "productItem"
    )
    @JsonManagedReference(
            value = "productItem-cartItem"
    )
    private CartItem cartItem;

    @OneToOne(
            mappedBy = "productItem"
    )
    @JsonManagedReference(
            value = "productItem-orderItem"
    )
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(
            name = "productId"
    )
    @JsonBackReference(
            value = "product-productItem"
    )
    private Product product;

    public ProductItem(String color, String size, Integer soldQuantity, Integer currentQuantity, Double price) {
        this.color = color;
        this.size = size;
        this.soldQuantity = soldQuantity;
        this.currentQuantity = currentQuantity;
        this.price = price;
    }
}
