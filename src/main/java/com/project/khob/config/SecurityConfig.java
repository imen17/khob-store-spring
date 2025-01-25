package com.project.khob.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.project.khob.domain.entities.UserRole.ADMIN;
import static com.project.khob.domain.entities.UserRole.USER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // This class was created to enable web security using:
    // @EnableWebSecurity AND @EnableMethodSecurity

    // These are the endpoints that anyone can access without authenticating
    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/products/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll() // Any request done in these urls will be allowed
                        .requestMatchers("/admin/**").hasAnyRole(ADMIN.name()) // these endpoints can only be accessed by an ADMIN
                        .requestMatchers("/cart/").hasAnyRole(USER.name()) // these endpoints can only be accessed by a USER
                        .anyRequest().authenticated() // The enforces authentication on the last 2 rules
                )
                // The session needs to be stateless since all requests need to pass the JWT filter
                // We cannot keep users logged in, in between requests
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // adding our custom Authentication provider
                .authenticationProvider(authenticationProvider)
                // adding our custom JWT filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }


}
