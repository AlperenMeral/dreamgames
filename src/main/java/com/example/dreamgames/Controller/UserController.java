package com.example.dreamgames.Controller;

import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser() {
        User user = new User();
        user.setLevel(1);
        user.setCoins(5000);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @PostMapping("/{id}")
    public User updateLevel(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLevel(user.getLevel() + 1);
            user.setCoins(user.getCoins() + 25);

            User savedUser = userRepository.save(user);
            return savedUser;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

}