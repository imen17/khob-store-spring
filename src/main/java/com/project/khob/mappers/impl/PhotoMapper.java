package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.PhotoDTO;
import com.project.khob.domain.entities.Photo;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper implements Mapper<Photo, PhotoDTO> {
    @Override
    public PhotoDTO mapTo(Photo photo) {
        return new PhotoDTO(
                photo.getUrl()
        );
    }

    @Override
    public Photo mapFrom(PhotoDTO photoDTO) {
        return new Photo(
                null,
                photoDTO.getUrl()
        );
    }
}
