package com.project.khob.config;

import com.project.khob.config.JwtService;
import com.project.khob.domain.entities.User;
import com.project.khob.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // These values are retrieved from "application.yml" properties
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // This function creates a HTTP Cookie from our token and the name that we give it (eg: accessToken)
    private String makeCookie(String name, String token, long expiration) {
        return ResponseCookie.from(name, token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(expiration)
                .build().toString();
    }

    // This function adds "accessToken" and "refreshToken" cookies to HttpServletResponse
    private void addCookiesToResponse(String username, HttpServletResponse response) {
        String accessToken = jwtService.generateToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        // set accessTokens to response header as SET_COOKIE
        response.addHeader(HttpHeaders.SET_COOKIE, makeCookie("accessToken",accessToken, jwtExpiration / 1000));
        response.addHeader(HttpHeaders.SET_COOKIE, makeCookie("refreshToken", refreshToken, refreshExpiration / 1000));
    }

    // This function checks if the token provided is valid and calls "addCookiesToResponse"
    public void login(UsernamePasswordAuthenticationToken authToken, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication.isAuthenticated()){
            User user = (User) authentication.getPrincipal();
            addCookiesToResponse(user.getUsername(), response);
        } else {
            throw new UsernameNotFoundException("Bad credentials.");
        }
    }

    // This function checks if the refresh token that is provided is valid for the user
    // and sends back new "accessToken" and "refreshToken" tokens
    public void refreshToken(
            String refreshToken,
            HttpServletResponse response
    ) throws Exception {
        String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) throw new Exception("Bad JWT token.");
        Optional<User> user = this.userRepository.findByEmail(userEmail);
        if (user.isEmpty()) throw new UsernameNotFoundException("Invalid user in JWT token.");
        if (!jwtService.isTokenValid(refreshToken, user.get())) throw new Exception("Old or invalid JWT token.");
        addCookiesToResponse(userEmail, response);
    }

    // This function sends back empty "accessToken" and "refreshToken" cookies in the logout request
    // effectively logging out the user
    public void logout(HttpServletResponse response) {
        response.addHeader(HttpHeaders.SET_COOKIE, makeCookie("accessToken","", 1));
        response.addHeader(HttpHeaders.SET_COOKIE, makeCookie("refreshToken", "", 1));
    }
}
