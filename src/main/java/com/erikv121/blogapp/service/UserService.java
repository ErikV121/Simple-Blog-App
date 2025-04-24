package com.erikv121.blogapp.service;

import com.erikv121.blogapp.dto.request.UserRequestDto;
import com.erikv121.blogapp.dto.response.UserResponseDto;
import com.erikv121.blogapp.entity.User;
import com.erikv121.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        // Check if passwords match
        if (!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

//        // Check if username already exists
//        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
//            throw new IllegalArgumentException("Username already exists");
//        }
//
//        // Check if email already exists
//        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
//            throw new IllegalArgumentException("Email already exists");
//        }

        // Create new user
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Convert to response DTO
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setRole(savedUser.getRole());
        responseDto.setCreatedAt(savedUser.getCreatedAt());

        return responseDto;
    }
}
