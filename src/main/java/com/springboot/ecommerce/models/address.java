package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class address {
    @Id
    @GeneratedValue
    private Integer id;

    private String country;

    private String city;

    private String street;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference
    private user user;

}
