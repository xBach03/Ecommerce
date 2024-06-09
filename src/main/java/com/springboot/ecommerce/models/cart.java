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
public class cart {
    @Id
    @GeneratedValue
    private Integer id;

    private Double totalPrice;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference
    private user user;

    @OneToMany(
            mappedBy = "cart"
    )
    @JsonManagedReference
    private List<cartItem> cartItemList;
}
