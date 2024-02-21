package com.project.khob.controllers;

import com.project.khob.domain.dto.UserDTO;
import com.project.khob.helpers.ValidationErrorResponse;
import com.project.khob.services.AuthenticationService;
import com.project.khob.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://192.168.1.100:5173"}, maxAge = 3600, allowCredentials = "true")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;


    @Operation(summary = "Login to the system using username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in successfully.")
    })
    @PostMapping("/login")
    public void AuthenticateAndGetCookie(@Valid @RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(),
                authRequestDTO.getPassword()
        );
        authService.login(token, response);
    }

    @Operation(summary = "Create an account in the system using username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400",  content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    })
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