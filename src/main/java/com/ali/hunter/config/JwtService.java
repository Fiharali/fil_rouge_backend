package com.ali.hunter.config;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.repository.UserRepository;
import com.ali.hunter.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Lazy
    private final UserRepository userService;



    private static final String SECRET_KEY = "3f+3wVmXQf4QCqaahmWBf/1Y9ysGnJsMImW4N7BJQDPqHtmWN8STm+S+7+7Jm0++EKd1y4Y/I8LNTly1ImSqKw==";

    public String extractEmail(String token) {
        try {
            String email = extractClaim(token, Claims::getSubject);
            return email;
        } catch (Exception e) {
            System.out.println("Error extracting email: " + e.getMessage());
            throw e;
        }
    }

    public String generateToken(Map<String , Object> extraClaims , UserDetails userDetails) {

        extraClaims.put("role", getUserRole(userDetails.getUsername()));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSingInKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();

    }

    private String getUserRole(String username) {
        User user = userService.findByEmail(username).get();
        return  user.getRole().name();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public <T> T extractClaim(String token, Function<Claims , T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return  Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token).getBody();
    }



    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
