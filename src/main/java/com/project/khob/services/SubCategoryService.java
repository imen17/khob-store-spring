package com.project.khob.services;

import com.project.khob.domain.entities.SubCategory;
import jakarta.persistence.EntityNotFoundException;

public interface SubCategoryService {

    SubCategory findOne(Long id) throws EntityNotFoundException;

}
