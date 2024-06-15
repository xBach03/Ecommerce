package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.order.OrderDto;
import com.springboot.ecommerce.models.Order;
import com.springboot.ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getPaymentInfo(),
                order.getStatus()
        );
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> findAllByUserId(Integer id) {
        return orderRepository.findAllByUserId(id).stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto save(Order order) {
        order.setCreatedDate(LocalDate.now());
        return toOrderDto(orderRepository.save(order));
    }
}
