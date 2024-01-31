package com.project.khob.services.impl;

import com.project.khob.domain.entities.Category;
import com.project.khob.repositories.CategoryRepository;
import com.project.khob.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findOne(Integer id) {
        return categoryRepository.findById(id);
    }
}
