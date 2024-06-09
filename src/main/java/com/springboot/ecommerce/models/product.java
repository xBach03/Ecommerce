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
@NoArgsConstructor
@AllArgsConstructor
public class product {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    private String rate;

    private LocalDate createdDate;

    @OneToOne
    @JoinColumn(
            name = "categoryId"
    )
    private category category;

    @ManyToOne
    @JoinColumn(
            name = "storeId"
    )
    @JsonBackReference
    private store store;

    @OneToOne(
            mappedBy = "product"
    )
    private voucher voucher;

    @OneToMany(
            mappedBy = "product"
    )
    @JsonManagedReference
    private List<productItem> productItemList;
}
