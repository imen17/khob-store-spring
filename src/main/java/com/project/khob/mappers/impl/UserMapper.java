package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.UserDTO;
import com.project.khob.domain.entities.Photo;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO mapTo(User user) {

        UserDTO.UserDTOBuilder userDTOBuilder = UserDTO.builder()
                .email(user.getEmail())
                .roles(user.getRoles())
                .firstName(user.getFirstName())
                .lastName(user.getLastName());
        Photo photo = user.getPhoto();
        if (photo != null) userDTOBuilder.photoUrl(photo.getUrl());
        return userDTOBuilder
                .build();
    }

    @Override
    public User mapFrom(UserDTO userDTO) {
        return User.builder()
                .photo(Photo.builder().url(userDTO.getPhotoUrl()).build())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .build();
    }
}
