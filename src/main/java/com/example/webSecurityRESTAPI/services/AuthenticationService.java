package com.example.webSecurityRESTAPI.services;

import com.example.webSecurityRESTAPI.exception.AuthenticationFailedException;
import com.example.webSecurityRESTAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String authenticate(String username, String password) throws AuthenticationFailedException {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            var user = (User)authentication.getPrincipal();
            String token = tokenService.generateToken(user);
            return token;
        } catch(AuthenticationException e) {
            throw new AuthenticationFailedException("Invalid username or password");
        }
    }
}
