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
@Table(name = "eUser")
public class user {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String phoneNumber;

    private String email;

    private String gender;

    private LocalDate createdDate;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<address> addressList;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<cart> cartList;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<order> orderList;

    @OneToMany(
            mappedBy = "user",
            cascade =  CascadeType.ALL
    )
    @JsonManagedReference
    private List<voucher> voucherList;
}
