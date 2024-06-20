package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
    List<ProductItem> findAllByProductId(Integer id);


}
