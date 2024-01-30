package com.project.khob.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.khob.domain.dto.AuthenticationRequest;
import com.project.khob.domain.dto.AuthenticationResponse;
import com.project.khob.domain.dto.RegisterRequest;
import com.project.khob.domain.entities.Token;
import com.project.khob.domain.entities.TokenType;
import com.project.khob.domain.entities.User;
import com.project.khob.domain.entities.UserRole;
import com.project.khob.repositories.TokenRepository;
import com.project.khob.repositories.UserRepository;
import com.project.khob.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
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

        // Compares generated token with the token for the user (throws Authentication exception)
        authenticationManager.authenticate(authToken);

        // If there is a math, get the User from the database
        var user = userRepository.findByEmail(request.getUsername()).orElseThrow(()->new RuntimeException("User doesn't exist"));

            //generate token using this user
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

       @Override
    public AuthenticationResponse register(RegisterRequest request) {

        //create User out of request
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) //encoding password before saving it
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

            //generate token
        var jwtToken = jwtService.generateToken(user);
        var savedUser = userRepository.save(user);

        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
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
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken); //extract the userEmail from jwt token.
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
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
