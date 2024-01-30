package com.project.khob.services.impl;

import com.project.khob.domain.entities.Photo;
import com.project.khob.repositories.PhotoRepository;
import com.project.khob.services.PhotoService;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    private PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }
}
