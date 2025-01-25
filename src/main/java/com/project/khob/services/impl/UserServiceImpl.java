package com.project.khob.services.impl;


import com.project.khob.domain.dto.UserDTO;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.impl.UserMapper;
import com.project.khob.repositories.UserRepository;
import com.project.khob.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public User create(UserDTO userDTO) throws Exception {
        if (findByUsername(userDTO.getEmail()).isPresent()){
            throw new Exception("User Already exists.");
        }
        String rawPassword = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = userMapper.mapFrom(userDTO);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;
    }

}
