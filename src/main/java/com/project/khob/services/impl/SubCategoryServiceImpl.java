package com.project.khob.services.impl;

import com.project.khob.domain.entities.SubCategory;
import com.project.khob.repositories.SubCategoryRepository;
import com.project.khob.services.SubCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory findOne(Long id) throws EntityNotFoundException {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (subCategory.isEmpty()) throw new EntityNotFoundException("Subcategory does not exist");
        return subCategory.get();
    }
}
