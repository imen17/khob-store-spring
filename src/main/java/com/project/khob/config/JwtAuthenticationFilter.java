package com.project.khob.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // This class extends the OncePerRequestFilter which means that it is called everytime a request is made

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    // This is the main function which intercepts requests on the securityFilterChain
    // It checks if an "accessToken" is provided in the cookies of the request
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Get the cookies from the request
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            // If there are no cookies, the function relays the verification to other classes down the chain
            filterChain.doFilter(request, response);
            return;
        }

        // Checking if a cookie with the name "accessToken" exists in the cookies
        String token = null;
        for(Cookie cookie: cookies) if(cookie.getName().equals("accessToken")) token = cookie.getValue();

        if(token == null){
            // If there is no cookie with that name, the function relays the verification to other classes down the chain
            filterChain.doFilter(request, response);
            return;
        }

        // We extract the username from the "payload" part of the JWT token
        String username = jwtService.extractUsername(token);

        if(username != null){ // If we can't find the username we skip
            // We get the actual user from the userDetails services by looking up the username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // We check if the token we received matches the user we pulled from the database
            if(jwtService.isTokenValid(token, userDetails)){
                // if this is true, we create a UsernamePasswordAuthenticationToken which can be used to tell the Application
                // that the aforementioned user is the one making all future requests
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        filterChain.doFilter(request, response);

    }
}
