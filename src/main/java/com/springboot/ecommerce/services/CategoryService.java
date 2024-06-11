package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.category.CategoryDto;
import com.springboot.ecommerce.models.Category;
import com.springboot.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getName(),
                category.getDescription()
        );
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findById(Integer id) {
        return categoryRepository.findById(id)
                .map(this::toCategoryDto)
                .orElse(null);
    }

    public CategoryDto save(Category category) {
        return toCategoryDto(categoryRepository.save(category));
    }
}
