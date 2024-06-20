package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderId(Integer id);


}
