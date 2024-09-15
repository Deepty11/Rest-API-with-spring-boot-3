package com.example.webSecurityRESTAPI.controllers;

import com.example.webSecurityRESTAPI.dto.AuthenticationRequest;
import com.example.webSecurityRESTAPI.dto.AuthenticationResponse;
import com.example.webSecurityRESTAPI.exception.AuthenticationFailedException;
import com.example.webSecurityRESTAPI.model.User;
import com.example.webSecurityRESTAPI.repositories.UserRepository;
import com.example.webSecurityRESTAPI.services.AuthenticationService;
import com.example.webSecurityRESTAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User newUser = userService.save(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws AuthenticationFailedException {
        String token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(new AuthenticationResponse(token));
    }
}
