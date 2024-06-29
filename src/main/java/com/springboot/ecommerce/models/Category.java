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
public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            unique = true
    )
    private String name;

    private String description;

    @OneToOne(
            mappedBy = "category"
    )
    private Product product;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
