package com.project.khob.controllers;

import com.project.khob.domain.dto.UserDTO;
import com.project.khob.helpers.ValidationErrorResponse;
import com.project.khob.config.AuthenticationService;
import com.project.khob.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.project.khob.domain.dto.AuthRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;

    @PostMapping("/login")
    public void AuthenticateAndGetCookie(@Valid @RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(),
                authRequestDTO.getPassword()
        );
        authService.login(token, response);
    }

    @GetMapping("/logout")
    public void Logout(HttpServletResponse response) {
        authService.logout(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUserAccount(@Valid @RequestBody UserDTO userDTO) throws Exception {
        userService.create(userDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Account created successfully");
    }

    @GetMapping("/refreshToken")
    public void refreshToken(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) throws Exception{
        authService.refreshToken(refreshToken, response);
    }

}