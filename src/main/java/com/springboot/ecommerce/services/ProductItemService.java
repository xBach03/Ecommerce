package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.productitem.ProductItemDto;
import com.springboot.ecommerce.models.ProductItem;
import com.springboot.ecommerce.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductItemService {
    private final ProductItemRepository productItemRepository;

    public ProductItemService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public ProductItemDto toProductItemDto(ProductItem productItem) {
        return new ProductItemDto(
                productItem.getColor(),
                productItem.getSize(),
                productItem.getSoldQuantity(),
                productItem.getCurrentQuantity(),
                productItem.getPrice()
        );
    }

    public List<ProductItemDto> findAll() {
        return productItemRepository.findAll().stream()
                .map(this::toProductItemDto)
                .collect(Collectors.toList());
    }
}
