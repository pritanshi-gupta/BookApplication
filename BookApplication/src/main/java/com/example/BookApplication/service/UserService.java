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

    //  ADD THIS
    @Autowired
    private PasswordEncoder passwordEncoder;

    //  UPDATED SIGNUP
    public User signup(User userRequest){

        // password encrypt
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return repo.save(userRequest);
    }

    public User login(String username){
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
