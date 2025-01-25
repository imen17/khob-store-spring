package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.GetMeResponseDTO;
import com.project.khob.domain.entities.Photo;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, GetMeResponseDTO> {
    @Override
    public GetMeResponseDTO mapTo(User user) {
        return GetMeResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .photoUrl(user.getPhoto().getUrl())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public User mapFrom(GetMeResponseDTO getMeResponseDTO) {
        return User.builder()
                .firstName(getMeResponseDTO.getFirstName())
                .lastName(getMeResponseDTO.getLastName())
                .roles(getMeResponseDTO.getRoles())
                .photo(Photo.builder().url(getMeResponseDTO.getPhotoUrl()).build())
                .build();
    }
}
