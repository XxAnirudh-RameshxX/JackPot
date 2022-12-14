package com.fidelity.jackpot.controller;

import com.fidelity.jackpot.exception.ResourceNotFoundException;
import com.fidelity.jackpot.exception.UserException;
import com.fidelity.jackpot.payload.*;
import com.fidelity.jackpot.security.JwtHelper;
import com.fidelity.jackpot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jackpot/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ResponseEntity<?> response = null;

        try {
            LoginResponse responseBody = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            if(responseBody != null) {
                Map<String, String> claims = new HashMap<>();
                claims.put("email", loginRequest.getEmail());
                claims.put("userId", responseBody.getUserId().toString());
                String jwt = jwtHelper.createJwtForClaims(loginRequest.getEmail(), claims);
                responseBody.setJwt(jwt);
                response = new ResponseEntity<>(responseBody, HttpStatus.OK);
            } else{
                response = new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (ResourceNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        ResponseEntity<?> response = null;

        try {
            SignupResponse responseBody = userService.signup(signupRequest);
            response = new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (UserException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(Authentication authentication, @RequestBody UpdateUserRequest updateUserRequest, @PathVariable("userId") Long userId) {
        ResponseEntity<?> response;
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        Map<String, Object> attributes = token.getTokenAttributes();
        if(Long.parseLong((String) attributes.get("userId")) == userId) {
            try {
                response = new ResponseEntity<>(userService.updateUser(userId, updateUserRequest), HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<>("Not authorized", HttpStatus.UNAUTHORIZED);
        }

        return response;
    }

}
