package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.product.ProductDto;
import com.springboot.ecommerce.models.Product;
import com.springboot.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDtoList = productService.findAll();
        return productDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/products/{product-name}")
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable("product-name") String name) {
        List<ProductDto> productDtoList = productService.findByName(name);
        return productDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> save(@RequestBody Product product) {
        ProductDto savedProduct = productService.save(product);
        return savedProduct == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/delete/{product-id}")
    public ResponseEntity<ProductDto> delete(@PathVariable("product-id") Integer id) {
        ProductDto deletedProduct = productService.delete(id);
        return deletedProduct == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}
