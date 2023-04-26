package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.security.JwtAuthenticationFilter;
import com.altf4omni.omnicmmc.service.MyUserDetails;
import com.altf4omni.omnicmmc.service.TokenService;
import groovy.util.logging.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
//@RestController
//@CrossOrigin//(origins = {"http://34.227.103.89:8080/AdminPage"})
public class CredentialsController {//extends UsernamePasswordAuthenticationFilter {
    private final TokenService tokenService;
    private final AuthenticationManagerBuilder authenticationManager;

    public CredentialsController(TokenService tokenService, AuthenticationManagerBuilder authenticationManager, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.tokenService = tokenService;
        //this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        // setFilterProcessesUrl("/login");
        this.authenticationManager = authenticationManager;
    }

//    @GetMapping("/AdminPage")
//    public ResponseEntity<String> getAdminToken() {
//        //get the token from the headers
//        String token = request.getHeader("Authorization");
//
//        boolean isValidToken = tokenService.validateToken(token);
//        if(isValidToken){
//            return ResponsEntity.ok("The token is valid.");
//        } else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//        }
//
//        return ResponseEntity.ok(token);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody MyUserDetails loginRequest) throws Exception {
        //authenticate the user
        Authentication authentication = authenticationManager.build().authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //generate the JWT
        String token = tokenService.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/check-token")
    public ResponseEntity<String> checkToken(HttpServletRequest request) {

        // retrieve token from headers
        String token = request.getHeader("Authorization");

        boolean isValidToken = tokenService.validateToken(token);

        if (isValidToken) {
            return ResponseEntity.ok("The token is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
