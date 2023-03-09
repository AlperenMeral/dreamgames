package com.example.dreamgames.Service;

import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateLevel(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLevel(user.getLevel() + 1);
            user.setCoins(user.getCoins() + 25);

            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public User createUser() {
        User user = new User();
        user.setLevel(1);
        user.setCoins(5000);

        return userRepository.save(user);
    }

}
