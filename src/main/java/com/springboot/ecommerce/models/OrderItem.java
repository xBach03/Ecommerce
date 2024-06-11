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
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "orderId"
    )
    private Order order;

    @OneToOne
    @JoinColumn(
            name = "productItemId"
    )
    private ProductItem productItem;
}
