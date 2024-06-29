package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.orderitem.OrderItemDto;
import com.springboot.ecommerce.models.OrderItem;
import com.springboot.ecommerce.repositories.OrderItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<OrderItemDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.findAll(pageable).stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> findAllByOrderId(Integer id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.findAllByOrderId(id, pageable).stream()
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
