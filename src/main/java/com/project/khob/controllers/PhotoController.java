package com.project.khob.controllers;

import com.project.khob.domain.dto.PhotoDto;
import com.project.khob.domain.entities.Photo;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.PhotoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {
    private PhotoService photoService;

    private Mapper<Photo, PhotoDto> photoMapper;

    public PhotoController(PhotoService photoService, Mapper<Photo, PhotoDto> photoMapper) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }

    //Create
    @PostMapping(path = "/photos/new")
    public ResponseEntity<PhotoDto> createPhoto(@RequestBody PhotoDto photoDto){
        Photo photo=photoMapper.mapFrom(photoDto);
        Photo savedPhoto=photoService.createPhoto(photo);
        return new ResponseEntity<>(photoMapper.mapTo(savedPhoto), HttpStatus.CREATED);
    }
}
