package com.project.khob.controllers;

import com.project.khob.domain.dto.CategoryDto;
import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.entities.Category;
import com.project.khob.domain.entities.Product;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Mapper<Category, CategoryDto> categoryMapper;

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        var category = categoryMapper.mapFrom(categoryDto);
        var savedCategory = categoryService.create(category);
        return new ResponseEntity<>(categoryMapper.mapTo(savedCategory), HttpStatus.CREATED);
    }

}
