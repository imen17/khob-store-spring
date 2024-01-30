package com.project.khob.services;

import com.project.khob.domain.entities.Photo;
import org.springframework.stereotype.Service;

@Service
public interface PhotoService {
    Photo createPhoto(Photo photo);
}
