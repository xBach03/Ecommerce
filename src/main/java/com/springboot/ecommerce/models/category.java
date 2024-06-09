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
public class category {
    @Id
    private Integer id;

    private String name;

    private String description;

    @OneToOne(
            mappedBy = "category"
    )
    private product product;

}
