package com.project.khob.services;

import com.project.khob.domain.dto.PhotoDTO;

import java.util.List;

public interface PhotoService {
    void save(PhotoDTO photoDTO);
    void saveAll(List<PhotoDTO> photoDTOs);
}
