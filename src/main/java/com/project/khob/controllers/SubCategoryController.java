package com.project.khob.controllers;

import com.project.khob.domain.dto.ApiErrorResponse;
import com.project.khob.domain.dto.CategoryDto;
import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.dto.SubCategoryDto;
import com.project.khob.domain.entities.Category;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.SubCategory;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.CategoryService;
import com.project.khob.services.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final Mapper<SubCategory, SubCategoryDto> subCategoryMapper;

    @PostMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Object> createSubCategory(@PathVariable("categoryId") Integer categoryId, @RequestBody SubCategoryDto subCategoryDto) {
        var category = categoryService.findOne(categoryId);
        if (category.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var subCategory = subCategoryMapper.mapFrom(subCategoryDto);
        subCategory.setCategory(category.get());
        var savedSubCategory = subCategoryService.create(subCategory);
        return new ResponseEntity<>(subCategoryMapper.mapTo(savedSubCategory), HttpStatus.CREATED);
    }

}
