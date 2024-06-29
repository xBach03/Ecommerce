package com.springboot.ecommerce.models;

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
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String rate;

    private String location;

    private LocalDate joiningDate;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "store-product"
    )
    private List<Product> productList;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "store-cartItem"
    )
    private List<CartItem> cartItemList;

    public Store(String location, String name, String rate) {
        this.location = location;
        this.name = name;
        this.rate = rate;
    }
}
