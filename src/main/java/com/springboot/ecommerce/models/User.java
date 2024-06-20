package com.springboot.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.ecommerce.auth.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String gender;

    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "user-address"
    )
    private List<Address> addressList;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "user-cart"
    )
    private List<Cart> cartList;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(
            value = "user-order"
    )
    private List<Order> orderList;

    @OneToMany(
            mappedBy = "user",
            cascade =  CascadeType.ALL
    )
    @JsonManagedReference(
            value = "user-voucher"
    )
    private List<Voucher> voucherList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User(String username, String password, String email, Role role, LocalDate date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdDate = date;
    }
}
