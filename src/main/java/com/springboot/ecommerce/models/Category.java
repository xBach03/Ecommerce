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

}
