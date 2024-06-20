package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.order.OrderDto;
import com.springboot.ecommerce.models.Order;
import com.springboot.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> orderList = orderService.findAll();
        if(orderList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/orders/{order-id}")
    public ResponseEntity<List<OrderDto>> findAllByUserId(@PathVariable("order-id") Integer id) {
        List<OrderDto> orderList = orderService.findAllByUserId(id);
        if(orderList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> save(@RequestBody Order order) {
        OrderDto savedOrder = orderService.save(order);
        return savedOrder == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/orders/delete/{order-id}")
    public ResponseEntity<OrderDto> delete(@PathVariable("order-id") Integer id) {
        OrderDto deletedOrder = orderService.delete(id);
        return deletedOrder == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedOrder, HttpStatus.OK);
    }
}
