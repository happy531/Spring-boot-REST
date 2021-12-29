package com.example.backend.user_profile;

import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user_profile")
public class UserProfileController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, UserService userService) {
        this.userProfileService = userProfileService;
        this.userService = userService;
    }

    @GetMapping(path = "{userID}")
    public Optional<UserProfile> getProfile(@PathVariable("userID") long id) {
        return userProfileService.getProfile(id);
    }

    @PostMapping(path = "{userID}")
    public void saveUserProfile(@RequestBody UserProfile userProfile, @PathVariable long userID) {
        User user = userService.getUser(userID).get();
        user.setUserProfile(null);
        userProfile.setUser(user);
        userProfileService.saveUserProfile(userProfile);
    }
}
