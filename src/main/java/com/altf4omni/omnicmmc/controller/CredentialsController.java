package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.service.UserService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class CredentialsController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody Map<String, String> json) {
        String username = json.get("username");
        String password = json.get("password");

        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(userDetails);
            }
        } catch (UsernameNotFoundException e) {
            // handle exception
        } catch (BadCredentialsException e) {
            // handle exception
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

