package com.sltrelnikov.resourceserver.controller;

import com.sltrelnikov.resourceserver.entity.User;
import com.sltrelnikov.resourceserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody User user) {
        if (userService.usernameExists(user)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already exists. Please choose a different username.");
        }
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }
}
