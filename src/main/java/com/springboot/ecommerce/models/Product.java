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
public class Product {
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
    @JsonBackReference(
            value = "category-product"
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "storeId"
    )
    @JsonBackReference(
            value = "store-product"
    )
    private Store store;

    @OneToOne(
            mappedBy = "product"
    )
    @JsonManagedReference(
            value = "voucher-product"
    )
    private Voucher voucher;

    @OneToMany(
            mappedBy = "product"
    )
    @JsonManagedReference(
            value = "product-productItem"
    )
    private List<ProductItem> productItemList;

    public Product(String name, String description, String rate) {
        this.name = name;
        this.description = description;
        this.rate = rate;
    }
}
