package com.example.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userID}")
    public Optional<User> getUser(@PathVariable("userID") long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PutMapping(path = "{userID}")
    public void updateUser(
            @PathVariable("userID") Long userID,
            @RequestBody User updatedUser
    ) {
        userService.updateUser(userID, updatedUser);
    }

    @DeleteMapping(path = "{userID}")
    public void deleteUser(@PathVariable("userID") long id) {
        userService.deleteUser(id);
    }

}
