package com.project.khob.services.impl;

import com.project.khob.domain.entities.SubCategory;
import com.project.khob.repositories.SubCategoryRepository;
import com.project.khob.services.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory create(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }
}
