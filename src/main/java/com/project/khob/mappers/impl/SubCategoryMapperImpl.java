package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CategoryDto;
import com.project.khob.domain.dto.PhotoDto;
import com.project.khob.domain.dto.SubCategoryDto;
import com.project.khob.domain.entities.Category;
import com.project.khob.domain.entities.Photo;
import com.project.khob.domain.entities.SubCategory;
import com.project.khob.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubCategoryMapperImpl implements Mapper<SubCategory, SubCategoryDto> {

    private final ModelMapper modelMapper;


    @Override
    public SubCategoryDto mapTo(SubCategory category) {
        return modelMapper.map(category,SubCategoryDto.class);
    }

    @Override
    public SubCategory mapFrom(SubCategoryDto photo) {
        return modelMapper.map(photo,SubCategory.class);
    }
}
