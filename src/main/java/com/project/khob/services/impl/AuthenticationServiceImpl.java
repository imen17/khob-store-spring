package com.project.khob.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.khob.domain.dto.AuthenticationRequest;
import com.project.khob.domain.dto.AuthenticationResponse;
import com.project.khob.domain.dto.RegisterRequestDto;
import com.project.khob.domain.dto.UsernameAlreadyExistsException;
import com.project.khob.domain.entities.Token;
import com.project.khob.domain.entities.TokenType;
import com.project.khob.domain.entities.User;
import com.project.khob.domain.entities.UserRole;
import com.project.khob.repositories.TokenRepository;
import com.project.khob.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserServiceImpl userService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Retrieve request body parameters
        var username = request.getUsername();
        var password = request.getPassword();

         // Generates a token from the credentials sent by the request
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );

        // Checks if the token is valid (throws Authentication exception)
        authenticationManager.authenticate(authToken);

        // If there is a match, get the User from the database
        var user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist."));

        // Generate a JWT token and a refresh token using this user
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Delete all older tokens
        revokeAllUserTokens(user);

        // Assign new JWT token to the user
        saveUserToken(user, jwtToken);

        // Return both tokens
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(User user) {

        // Check if user already exists
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistsException("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Persist the user in the database
        var savedUser = userService.createUser(user);

        // Generate JWT tokens
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Assign JWT token to user
        saveUserToken(savedUser, jwtToken);

        // Return JWT tokens
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Checks if the request is correctly formed
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }

        // Retrieve the refresh token
        final String refreshToken = authHeader.substring(7);

        // Retrieve the username. ie: email
        final String username = jwtService.extractUsername(refreshToken);

        if (username == null) {
            return;
        }

        // Check if user exists
        var user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist."));

        // Check if refresh token is valid
        if (!jwtService.isTokenValid(refreshToken, user)) {
            return;
        }

        // Generate new token and clear older ones
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);


        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);


    }

    @Override
    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }



    @Override
    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
