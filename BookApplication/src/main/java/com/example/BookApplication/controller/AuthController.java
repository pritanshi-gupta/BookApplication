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
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔹 SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest) {
        User user = service.signup(userRequest);

        return ResponseEntity.ok("User registered. OTP: " + user.getOtp());
    }

    // OTP VERIFY
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String username, @RequestParam String otp) {

        String result = service.verifyOtp(username, otp);

        return ResponseEntity.ok(result);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = service.login(user.getUsername());

        // password check
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid Password");
        }

        // OTP verified check
        if (!existingUser.isVerified()) {
            return ResponseEntity.status(403).body("Please verify OTP first");
        }

        // JWT generate
        String token = JwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(token);
    }
}
