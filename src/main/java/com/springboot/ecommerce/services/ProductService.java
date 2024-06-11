package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.product.ProductDto;
import com.springboot.ecommerce.models.Product;
import com.springboot.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getRate(),
                product.getCreatedDate()
        );
    }

    public List<ProductDto> findByName(String name) {
        return productRepository.findAllByName(name)
                .stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }
}
