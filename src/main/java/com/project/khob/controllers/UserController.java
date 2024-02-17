package com.project.khob.controllers;


import com.project.khob.domain.dto.GetMeResponseDTO;
import com.project.khob.config.JwtService;
import com.project.khob.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://192.168.1.100:5173"}, maxAge = 3600, allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @GetMapping(path= "/me")
    public GetMeResponseDTO getMe(@AuthenticationPrincipal UserDetails userDetails) { // We can use UserDetails as our User class implements it
        return modelMapper.map(userDetails, GetMeResponseDTO.class);
    }
//    @PatchMapping (path = "/changePsw")
//    public ResponseEntity<?> changePassword(
//            @RequestBody ChangePasswordRequest request,
//            Principal connectedUser
//    ) {
//        userService.changePassword(request, connectedUser);
//        return ResponseEntity.ok().build();
//    }

    //listAll
//    @GetMapping()
//    public Page<UserDTO> listProducts(Pageable pageable){
//        Page<UserInfo> users=userService.findAll(pageable);
//        return users.map(userMapper::mapTo);
//    }
//
//    //listOne
//    @GetMapping (path = "/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
//        Optional<UserInfo> foundUser=userService.findOne(id);
//        return foundUser.map(user ->{
//            UserDTO userDto =   userMapper.mapTo(user);
//            return new ResponseEntity<>(userDto,HttpStatus.OK);
//        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PatchMapping (path = "/{id}")
//    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDto) {
//        if (!userService.isExists(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        UserInfo product=userMapper.mapFrom(userDto);
//        UserInfo savedProduct=userService.partialUpdate(id, product);
//        return new ResponseEntity<>(userMapper.mapTo(savedProduct),HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<UserDTO> deleteProduct(@PathVariable("id") Long id){
//        userService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
