package az.developia.school.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") 
    private String secret;

    @Value("${jwt.expiration-ms}") 
    private long expirationMs;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Генерация JWT токена для пользователя
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) 
                .setIssuedAt(new Date()) 
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) 
                .signWith(getSigningKey())
                .compact();
    }


    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

 
    public boolean validateToken(String token) {
        try {
            getClaims(token); 
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

  
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) 
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
