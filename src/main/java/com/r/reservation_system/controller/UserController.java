package com.r.reservation_system.controller;

import com.r.reservation_system.entity.User;
import com.r.reservation_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @jakarta.validation.Valid User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}