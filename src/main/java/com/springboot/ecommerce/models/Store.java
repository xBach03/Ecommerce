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
    @JsonManagedReference
    private List<Product> productList;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<CartItem> cartItemList;
}
