package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name, Pageable pageable);

    @Query(
            value = "SELECT p.* " +
                    "FROM Product p " +
                    "INNER JOIN Category c ON p.category_id = c.id " +
                    "WHERE c.name = ?1",
            nativeQuery = true
    )
    List<Product> findAllByCategory(String name);
}
