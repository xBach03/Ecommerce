package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.category.CategoryDto;
import com.springboot.ecommerce.models.Category;
import com.springboot.ecommerce.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> categoryList = categoryService.findAll();
        if(categoryList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/categories/{category-id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("category-id") Integer id) {
        CategoryDto categoryDto = categoryService.findById(id);
        return categoryDto == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> save(@RequestBody Category category) {
        CategoryDto savedCategory = categoryService.save(category);
        return savedCategory == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/categories/delete/{category-id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable("category-id") Integer id) {
        CategoryDto deletedCategory = categoryService.delete(id);
        return deletedCategory == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }
}
