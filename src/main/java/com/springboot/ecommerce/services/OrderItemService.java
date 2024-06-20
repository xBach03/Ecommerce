package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.orderitem.OrderItemDto;
import com.springboot.ecommerce.models.OrderItem;
import com.springboot.ecommerce.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getOrder(),
                orderItem.getProductItem()
        );
    }

    public List<OrderItemDto> findAll() {
        return orderItemRepository.findAll().stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> findAllByOrderId(Integer id) {
        return orderItemRepository.findAllByOrderId(id).stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }

    public OrderItemDto save(OrderItem orderItem) {
        return toOrderItemDto(orderItemRepository.save(orderItem));
    }

    public OrderItemDto delete(Integer id) {
        OrderItemDto deletedOrderItem = orderItemRepository.findById(id).map(this::toOrderItemDto).orElse(null);
        orderItemRepository.deleteById(id);
        return deletedOrderItem;
    }
}
