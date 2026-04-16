package com.example.BookApplication.Controller;


import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest){
        User user = service.signup(userRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User existingUser = service.login(user.getUsername());

        if(existingUser.getPassword().equals(user.getPassword())) {
            return "Login Successful";
        }
        else{
            return "Invalid Password";
        }
    }
}
