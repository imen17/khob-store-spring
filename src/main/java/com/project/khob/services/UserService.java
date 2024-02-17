package com.project.khob.services;


import com.project.khob.domain.dto.UserDTO;
import com.project.khob.domain.entities.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByUsername(String username);
    User create(UserDTO userDTO) throws Exception;

}
