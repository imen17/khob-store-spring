package com.project.khob.controllers;


import com.project.khob.domain.dto.GetMeResponseDTO;
import com.project.khob.config.JwtService;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.impl.GetMeResponseMapper;
import com.project.khob.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final GetMeResponseMapper getMeResponseMapper;

    @GetMapping(path= "/me")
    public GetMeResponseDTO getMe(@AuthenticationPrincipal User user) { // We can use UserDetails as our User class implements it
        return getMeResponseMapper.mapTo(user);
    }

}
