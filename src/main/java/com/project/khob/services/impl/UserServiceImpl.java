package com.project.khob.services.impl;


import com.project.khob.domain.dto.UserDTO;
import com.project.khob.domain.entities.User;
import com.project.khob.repositories.UserRepository;
import com.project.khob.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

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
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;
    }
//    @Override
//    public UserInfo createUser(UserInfo userInfo) {
//        return userRepository.save(userInfo);
//    }
//
//    @Override
//    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
//        var user = (UserInfo) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//
//        // check if the current password is correct
//        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
//            throw new IllegalStateException("Wrong password");
//        }
//        // check if the two new passwords are the same
//        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
//            throw new IllegalStateException("Password are not the same");
//        }
//
//        // update the password
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//
//        // save the new password
//        userRepository.save(user);
//    }
//
//    @Override
//    public Optional<UserInfo> findOne(Long id) {
//        return userRepository.findById(id);
//    }

//    @Override
//    public Page<UserInfo> findAll(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

//    @Override
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public UserInfo partialUpdate(Long id, UserInfo userInfo) {
//        userInfo.setUserId(id);
//        return userRepository.findById(id).map(existingUser -> {
//            Optional.ofNullable(userInfo.getFirstName()).ifPresent(existingUser::setFirstName);
//            Optional.ofNullable(userInfo.getLastName()).ifPresent(existingUser::setLastName);
//            Optional.ofNullable(userInfo.getPhone()).ifPresent(existingUser::setPhone);
//            return userRepository.save(existingUser);
//        }).orElseThrow(()->new RuntimeException("user doesn't exist"));
//    }
//
//    @Override
//    public boolean isExists(Long id) {
//        return userRepository.existsById(id);
//    }




//    @Override
//    public UserDTO getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
//        String usernameFromAccessToken = userDetail.getUsername();
//        Optional<UserInfo> user = userRepository.findByUsername(usernameFromAccessToken);
//        return modelMapper.map(user, UserDTO.class);
//    }

}
