package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
