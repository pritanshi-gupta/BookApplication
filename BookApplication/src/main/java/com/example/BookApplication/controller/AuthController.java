package com.example.BookApplication.controller;

import com.example.BookApplication.entity.User;
import com.example.BookApplication.security.JwtUtil;
import com.example.BookApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    //  ADD THIS
    @Autowired
    private PasswordEncoder passwordEncoder;

    //  SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest) {

        User user = service.signup(userRequest);

        return ResponseEntity.ok(user);
    }

    // LOGIN (UPDATED)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = service.login(user.getUsername());


        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {

            String token = JwtUtil.generateToken(user.getUsername());

            return ResponseEntity.ok(token);

        } else {
            return ResponseEntity.status(401).body("Invalid Password");
        }
    }
}
