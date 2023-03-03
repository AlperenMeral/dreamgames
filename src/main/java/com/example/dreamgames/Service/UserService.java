package com.example.dreamgames.Service;

import com.example.dreamgames.Repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
