package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class UserService {

    @Autowired
    private UserRepository repo;

    public User signup(User userRequest){
        return repo.save(userRequest);
    }
    public User login(String username){
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
