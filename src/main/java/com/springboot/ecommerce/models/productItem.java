package com.springboot.ecommerce.models;

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
public class productItem {
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
    private cartItem cartItem;

    @OneToOne(
            mappedBy = "productItem"
    )
    private orderItem orderItem;

    @ManyToOne
    @JoinColumn(
            name = "productId"
    )
    private product product;

}
