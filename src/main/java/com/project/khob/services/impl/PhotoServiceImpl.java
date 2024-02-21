package com.project.khob.services.impl;

import com.project.khob.domain.dto.PhotoDTO;
import com.project.khob.domain.entities.Photo;
import com.project.khob.mappers.impl.PhotoMapper;
import com.project.khob.repositories.PhotoRepository;
import com.project.khob.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private  final PhotoRepository photoRepository;
    private  final PhotoMapper photoMapper;
    @Override
    public void save(PhotoDTO photoDTO) {
        Photo photo = photoMapper.mapFrom(photoDTO);
        photoRepository.save(photo);
    }

    @Override
    public void saveAll(List<PhotoDTO> PhotoDTOs) {
        List<Photo> photos = PhotoDTOs.stream().map(photoMapper::mapFrom).collect(Collectors.toList());
        photoRepository.saveAll(photos);
    }


}
