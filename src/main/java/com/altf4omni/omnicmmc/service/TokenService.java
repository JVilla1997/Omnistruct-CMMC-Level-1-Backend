package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private static final String secretKey = "my-secret-key";
    @Value("${jwt.expiration}")
    private int expirationTime;

    @Autowired
    private UserRepository userRepository;
//    public String generateToken(String username) throws NoSuchAlgorithmException{
//        String data = username + SECRET; //concatenate the username and key
//
//        //hash the concatenated data using algorithm SHA-256
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
//
//        //encode the hash byte using Base64
//        String hashString = Base64.getEncoder().encodeToString(hashBytes);
//
//        //this will return the hash token
//        return hashString;
//    }
    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String token) {
        final String tokenUsername = extractClaim(token, Claims::getSubject);
        User user = userRepository.getUserByUsername(tokenUsername);
        return (user != null && !isTokenExpired(token));
    }
}
