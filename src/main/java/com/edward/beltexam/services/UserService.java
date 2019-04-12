package com.edward.beltexam.services;

import java.util.Map;
import java.util.Optional;

import com.edward.beltexam.models.User;
import com.edward.beltexam.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(Map<String, String> body) {
        User user = new User(body);
        this.userRepository.save(user);
        return user;
    }

    public User findUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}