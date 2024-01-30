package com.project.khob.controllers;

import com.project.khob.domain.dto.ChangePasswordRequest;
import com.project.khob.domain.dto.UserDto;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private Mapper<User, UserDto> userMapper;

    public UserController(UserService userService, Mapper<User, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PatchMapping (path = "/changePsw")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    //listAll
    @GetMapping()
    public Page<UserDto> listProducts(Pageable pageable){
        Page<User> users=userService.findAll(pageable);
        return users.map(userMapper::mapTo);
    }

    //listOne
    @GetMapping (path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        Optional<User> foundUser=userService.findOne(id);
        return foundUser.map(user ->{
            UserDto userDto =   userMapper.mapTo(user);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if (!userService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User product=userMapper.mapFrom(userDto);
        User savedProduct=userService.partialUpdate(id, product);
        return new ResponseEntity<>(userMapper.mapTo(savedProduct),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> deleteProduct(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
