package com.example.it.project.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "itproject_sid";

    // Extract student id as string
    public String extractStudentId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract subject id
    public Long extractSubjectId(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("subjectId", Long.class);
    }

    // Extract token expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract general claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract every claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Generate claims from original data
    // Student + Subject (including id, name, etc.)
    public String generateToken(Long id, String name,
                                String email, Long subjectId, String subjectName) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name);
        claims.put("email", email);
        claims.put("subjectId", subjectId);
        claims.put("subjectName", subjectName);
        return createToken(claims, id.toString());
    }

    // Create token using claims generated above
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
