package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUserId(Integer id);


}
