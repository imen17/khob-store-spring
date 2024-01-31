package com.project.khob.services;

import com.project.khob.domain.entities.Category;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);
    Optional<Category> findOne(Integer id);
}
