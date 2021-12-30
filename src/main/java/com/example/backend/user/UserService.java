package com.example.backend.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        Optional<User> userByNick = Optional.ofNullable(userRepository.findUserByNick(user.getNick()));
        if (userByEmail.isPresent() || userByNick.isPresent()) {
            throw new IllegalStateException("email or nick is already used");
        }
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userID, User updatedUser) {
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalStateException(
                "user with id " + userID + "does not exists"));
        if (updatedUser.getNick() == null || Objects.equals(updatedUser.getNick(), user.getNick())) {
            throw new IllegalStateException("this nick is already taken");
        }

        if (updatedUser.getEmail() == null || updatedUser.getEmail().length() <= 0 || Objects.equals(updatedUser.getEmail(), user.getEmail())) {
            throw new IllegalStateException("this email is already taken");
        }

        updatedUser.setId(userID);
        userRepository.save(updatedUser);
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("user " + id + " does not exists");
        }
        userRepository.deleteAllById(Collections.singleton(id));
    }


}
