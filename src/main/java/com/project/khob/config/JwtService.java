package com.project.khob.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // These values are taken from "application.yml" properties
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // This function extracts the username from the "payload" section JWT Token
    // Reference: https://jwt.io/
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // This function extracts the expiry date from the JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // This a helper function that helps us extract claims by their name from any JWT token
    // A claim is a property in the "payload" section of a JWT token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // This function extracts all the claims from a JWT token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // This function checks if a JWT token is expired by comparing it's expiry date to the current date
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // This function checks the validity of the token by comparing username of the User to the username in the JWT token
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // This function generates a JWT token that contains "username" as part of it's "payload" section
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,username, jwtExpiration);
    }

    // This function generates a JWT token like the function above but with longer expiry date

    public String generateRefreshToken(
            String username
    ) {
        return createToken(new HashMap<>(), username, refreshExpiration);
    }

    // This is a helper function that creates a JWT token with whatever claims we provide it. We must provide it
    // at least a username as that is necessary for authentication
    // we also provide it the expiry time of the token

    private String createToken(
            Map<String, Object> extraClaims,
            String username,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // This function decodes the signing key from our "application.yml"
    // This key is secret and only the server has it
    // This way, no one else can forge our JWT tokens
    private Key getSigningkey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}