package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.productitem.ProductItemDto;
import com.springboot.ecommerce.models.ProductItem;
import com.springboot.ecommerce.repositories.ProductItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<ProductItemDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productItemRepository.findAll().stream()
                .map(this::toProductItemDto)
                .collect(Collectors.toList());
    }

    public List<ProductItemDto> findAllByProductId(Integer id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productItemRepository.findAllByProductId(id, pageable).stream()
                .map(this::toProductItemDto)
                .collect(Collectors.toList());
    }

    public ProductItemDto save(ProductItem productItem) {
        return toProductItemDto(productItemRepository.save(productItem));
    }

    public ProductItemDto delete(Integer id) {
        ProductItemDto deletedProductItem = productItemRepository.findById(id).map(this::toProductItemDto).orElse(null);
        productItemRepository.deleteById(id);
        return deletedProductItem;
    }
}
