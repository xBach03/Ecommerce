package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;

    private Double totalPrice;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference(
            value = "user-cart"
    )
    private User user;

    @OneToMany(
            mappedBy = "cart"
    )
    @JsonManagedReference(
            value = "cart-cartItem"
    )
    private List<CartItem> cartItemList;
}
