package com.example.dreamgames.Controller;

import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.UserRepository;
import com.example.dreamgames.Service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public User createUser() {
        return userService.createUser();
    }

    @PostMapping("/{id}")
    public User updateLevel(@PathVariable long id) {
        return userService.updateLevel(id);
    }

}