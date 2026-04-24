package com.example.BookApplication.service;

import com.example.BookApplication.entity.User;
import com.example.BookApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // OTP generate
    public String generateOtp() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    // SIGNUP
    public User signup(User userRequest){

        // encrypt
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // OTP generate
        userRequest.setOtp(generateOtp());

        userRequest.setVerified(false);

        return repo.save(userRequest);
    }

    // LOGIN
    public User login(String username){
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // OTP VERIFY
    public String verifyOtp(String username, String otp){
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getOtp().equals(otp)){
            user.setVerified(true);
            repo.save(user);
            return "OTP Verified";
        }

        return "Invalid OTP";
    }
}