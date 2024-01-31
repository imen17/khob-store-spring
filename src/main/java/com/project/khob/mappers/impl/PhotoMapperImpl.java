package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.PhotoDto;
import com.project.khob.domain.entities.Photo;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class PhotoMapperImpl implements Mapper<Photo, PhotoDto> {

    private final ModelMapper modelMapper;

    public PhotoMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PhotoDto mapTo(Photo photo) {
        return modelMapper.map(photo,PhotoDto.class);
    }

    @Override
    public Photo mapFrom(PhotoDto photoDto) {
        return modelMapper.map(photoDto,Photo.class);
    }
}
