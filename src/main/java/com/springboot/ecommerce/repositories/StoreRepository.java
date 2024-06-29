package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findAllByNameContaining(String name, Pageable pageable);


}
