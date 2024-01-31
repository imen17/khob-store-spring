package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CategoryDto;
import com.project.khob.domain.dto.PhotoDto;
import com.project.khob.domain.entities.Category;
import com.project.khob.domain.entities.Photo;
import com.project.khob.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements Mapper<Category, CategoryDto> {

    private final ModelMapper modelMapper;


    @Override
    public CategoryDto mapTo(Category category) {
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public Category mapFrom(CategoryDto photo) {
        return modelMapper.map(photo,Category.class);
    }
}
