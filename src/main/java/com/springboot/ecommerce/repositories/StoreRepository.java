package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
