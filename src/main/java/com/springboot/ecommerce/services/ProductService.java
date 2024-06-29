package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.product.ProductDto;
import com.springboot.ecommerce.models.Product;
import com.springboot.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ProductDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable).stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findByName(String name, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAllByName(name, pageable)
                .stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto save(Product product) {
        product.setCreatedDate(LocalDate.now());
        return toProductDto(productRepository.save(product));
    }

    public ProductDto delete(Integer id) {
        ProductDto deletedProduct = productRepository.findById(id).map(this::toProductDto).orElse(null);
        productRepository.deleteById(id);
        return deletedProduct;
    }
}
