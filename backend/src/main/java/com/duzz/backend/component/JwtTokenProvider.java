package com.duzz.backend.component;

import com.duzz.backend.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomUserDetailsService customUserDetailsService;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private SecretKey key;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> claims = Map.of("username", userDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getIdFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            return !getClaimsFromToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        if (!validateToken(token)) {
            return null;
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getIdFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(secretKey.getBytes());
        }
        return key;
    }
}
