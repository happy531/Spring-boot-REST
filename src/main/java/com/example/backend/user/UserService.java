package com.example.backend.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public void registerUser(@NotNull User user) {
        Optional<User> userByEmail = Optional.ofNullable(userRepository.findUserByEmail(user.getEmail()));
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("email is already used");
        }
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("user " + id + " does not exists");
        }
        userRepository.deleteAllById(Collections.singleton(id));
    }


}
