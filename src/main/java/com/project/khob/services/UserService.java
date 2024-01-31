package com.project.khob.services;

import com.project.khob.domain.dto.ChangePasswordRequest;
import com.project.khob.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByEmail(String email);
    User createUser(User user);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    Page<User> findAll(Pageable pageable);

    Optional<User> findOne(Long id);

    boolean isExists(Long id);

    User partialUpdate(Long id, User product);

    void delete(Long id);
}
