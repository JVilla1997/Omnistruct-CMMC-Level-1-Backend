package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.security.JwtAuthenticationFilter;
import com.altf4omni.omnicmmc.security.JwtResponse;
import com.altf4omni.omnicmmc.service.MyUserDetails;
import com.altf4omni.omnicmmc.service.TokenService;
import groovy.lang.Lazy;
import groovy.util.logging.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
//@RestController
//@CrossOrigin//(origins = {"http://34.227.103.89:8080/AdminPage"})
public class CredentialsController {//extends UsernamePasswordAuthenticationFilter {
    private final TokenService tokenService;
    @Lazy
    private final AuthenticationManagerBuilder authenticationManager;

    public CredentialsController(TokenService tokenService, AuthenticationManagerBuilder authenticationManager, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody MyUserDetails loginRequest) throws Exception {
        // authenticate the user
        Authentication authentication = authenticationManager.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // generate the JWT
        String token = tokenService.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(token, userDetails.getUsername(), roles);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/check-token")
    public ResponseEntity<JwtResponse> checkToken(HttpServletRequest request) {

        // retrieve token from headers
        String token = request.getHeader("Authorization");

        boolean isValidToken = tokenService.validateToken(token);

        if (isValidToken) {
            UserDetails userDetails = tokenService.getUserDetailsFromToken(token);
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            // generate the JWT
            JwtResponse jwtResponse = new JwtResponse(token, userDetails.getUsername(), roles);
            return ResponseEntity.ok(jwtResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
