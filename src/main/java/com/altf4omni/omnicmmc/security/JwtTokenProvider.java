package com.altf4omni.omnicmmc.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    public static final String SECRET_KEY = "OmniCMMC"; //randomKey we NEED to make into constant
    public static final long EXPIRATION_TIME = 86400000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer";

    private AuthenticationManager authenticationManager = null;

    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    public JwtTokenProvider(AuthenticationManager authenticationManagerBean, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        //this.jwtTokenProvider = jwtTokenProvider;
    }

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRATION_TIME);

        //add the roles of the user
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", roles);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * this method will check if the JWT token is valid when parsed
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            System.out.println("Invalid JWT signature.");
        }catch(MalformedJwtException ex){
            System.out.println("Invalid JWT token.");
        }
        return false;
    }
}
