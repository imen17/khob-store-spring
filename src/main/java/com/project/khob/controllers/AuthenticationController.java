package com.project.khob.controllers;


import com.project.khob.domain.dto.AuthenticationErrorResponse;
import com.project.khob.domain.dto.AuthenticationRequest;
import com.project.khob.domain.entities.User;
import com.project.khob.services.impl.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(bindingResult.getAllErrors());
        }
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody @Valid User user, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuthenticationErrorResponse.builder().message(bindingResult.getFieldError().toString()).build());
        }
        try {
            return ResponseEntity.ok(authenticationService.register(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuthenticationErrorResponse.builder().message(e.getMessage()).build());
        }

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
