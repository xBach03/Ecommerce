package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.orderitem.OrderItemDto;
import com.springboot.ecommerce.models.OrderItem;
import com.springboot.ecommerce.services.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/items/{page-number}/{page-size}")
    public ResponseEntity<List<OrderItemDto>> findAll(@PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<OrderItemDto> orderItemDtoList = orderItemService.findAll(pageNumber, pageSize);
        return orderItemDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(orderItemDtoList, HttpStatus.OK);
    }

    @GetMapping("/items/{order-id}/{page-number}/{page-size}")
    public ResponseEntity<List<OrderItemDto>> findAllByOrderId(@PathVariable("order-id") Integer id, @PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<OrderItemDto> orderItemDtoList = orderItemService.findAllByOrderId(id, pageNumber, pageSize);
        return orderItemDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(orderItemDtoList, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<OrderItemDto> save(@RequestBody OrderItem orderItem) {
        OrderItemDto savedOrderItem = orderItemService.save(orderItem);
        return savedOrderItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedOrderItem, HttpStatus.OK);
    }

    @DeleteMapping("/items/delete/{item-id}")
    public ResponseEntity<OrderItemDto> delete(@PathVariable("item-id") Integer id) {
        OrderItemDto deletedOrderItem = orderItemService.delete(id);
        return deletedOrderItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedOrderItem, HttpStatus.OK);
    }

}
