package com.project.khob.services.impl;

import com.project.khob.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        // Check if request contains auth header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);

        // Retrieve the token from the database
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);

        if (storedToken == null) {
            return;
        }

        // Set the token as expired
        storedToken.setExpired(true);
        storedToken.setRevoked(true);

        // Persist it to the database;
        tokenRepository.save(storedToken);

        // Clear security context to enforce the logout;
        SecurityContextHolder.clearContext();
    }
}
