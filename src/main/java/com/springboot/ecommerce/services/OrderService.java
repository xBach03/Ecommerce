package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.order.OrderDto;
import com.springboot.ecommerce.models.Order;
import com.springboot.ecommerce.repositories.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<OrderDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageable).stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> findAllByUserId(Integer id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAllByUserId(id, pageable).stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto save(Order order) {
        order.setCreatedDate(LocalDate.now());
        return toOrderDto(orderRepository.save(order));
    }

    public OrderDto delete(Integer id) {
        OrderDto deletedOrder = orderRepository.findById(id).map(this::toOrderDto).orElse(null);
        orderRepository.deleteById(id);
        return deletedOrder;
    }
}
