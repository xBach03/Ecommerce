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

    @GetMapping("/products/{page-number}/{page-size}")
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<ProductDto> productDtoList = productService.findAll(pageNumber, pageSize);
        return productDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/products/{product-name}/{page-number}/{page-size}")
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable("product-name") String name, @PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<ProductDto> productDtoList = productService.findByName(name, pageNumber, pageSize);
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

    @GetMapping("/products/{c-name}")
    public ResponseEntity<List<ProductDto>> getByCategory(@PathVariable("c-name") String name) {
        List<ProductDto> productDtos = productService.findByCategory(name);
        return productDtos == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
