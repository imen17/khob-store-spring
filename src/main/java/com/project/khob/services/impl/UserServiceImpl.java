package com.project.khob.services.impl;

import com.project.khob.domain.dto.ChangePasswordRequest;
import com.project.khob.domain.entities.User;
import com.project.khob.repositories.UserRepository;
import com.project.khob.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User partialUpdate(Long id, User user) {
        user.setUserId(id);
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(user.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(user.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(user.getPhone()).ifPresent(existingUser::setPhone);
            return userRepository.save(existingUser);
        }).orElseThrow(()->new RuntimeException("user doesn't exist"));
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }



}
