package com.project.khob.services;

import com.project.khob.domain.dto.AuthenticationRequest;
import com.project.khob.domain.dto.AuthenticationResponse;
import com.project.khob.domain.dto.RegisterRequest;
import com.project.khob.domain.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate (AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void revokeAllUserTokens(User user);
    void saveUserToken(User user, String jwtToken);
}
