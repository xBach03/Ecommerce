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
@Table(name = "eOrder")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate createdDate;

    private Double totalPrice;

    private String paymentInfo;

    private String status;

    @ManyToOne
    @JoinColumn(
            name = "eUserId"
    )
    @JsonBackReference(
            value = "user-order"
    )
    private User user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "order-orderItem"
    )
    private List<OrderItem> orderItemList;
}
