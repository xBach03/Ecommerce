package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.productitem.ProductItemDto;
import com.springboot.ecommerce.models.ProductItem;
import com.springboot.ecommerce.services.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-item")
public class ProductItemController {
    private final ProductItemService productItemService;

    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @GetMapping("/items/{page-number}/{page-size}")
    public ResponseEntity<List<ProductItemDto>> findAll(@PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<ProductItemDto> productItemDtoList = productItemService.findAll(pageNumber, pageSize);
        return productItemDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productItemDtoList, HttpStatus.OK);
    }

    @GetMapping("/items/{product-id}/{page-number}/{page-size}")
    public ResponseEntity<List<ProductItemDto>> findAllByProductId(@PathVariable("product-id") Integer id, @PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<ProductItemDto> productItemDtoList = productItemService.findAllByProductId(id, pageNumber, pageSize);
        return productItemDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productItemDtoList, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<ProductItemDto> save(@RequestBody ProductItem productItem) {
        ProductItemDto savedProductItem = productItemService.save(productItem);
        return savedProductItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedProductItem, HttpStatus.OK);
    }

    @DeleteMapping("/items/delete/{item-id}")
    public ResponseEntity<ProductItemDto> delete(@PathVariable("item-id") Integer id) {
        ProductItemDto deletedProductItem = productItemService.delete(id);
        return deletedProductItem == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedProductItem, HttpStatus.OK);
    }
}
