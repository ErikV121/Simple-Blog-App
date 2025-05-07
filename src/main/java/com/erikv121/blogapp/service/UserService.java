package com.erikv121.blogapp.service;

import com.erikv121.blogapp.entity.User;
import com.erikv121.blogapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createOrCheckUserExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
// do nothing
            System.out.println("User already exists");
        } else {
            System.out.println("User does not exist, creating new user");
            User newUser = new User();
            newUser.setUsername(username);
            userRepository.save(newUser);
        }
    }
}
