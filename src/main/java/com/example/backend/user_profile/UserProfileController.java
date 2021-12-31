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
    public void saveUserProfile(@PathVariable long userID, @RequestBody UserProfile userProfile) {
        Optional<User> userOptional = userService.getUser(userID);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("This profile does not exists");
        }
        User user = userOptional.get();
        user.setUserProfile(null);
        userProfile.setUser(user);
        userProfileService.saveUserProfile(userProfile);
    }

    @PutMapping(path = "{userID}")
    public void updateUserProfile(
            @PathVariable("userID") Long userID,
            @RequestBody UserProfile updatedUserProfile
    ) {
        userProfileService.updateUserProfile(userID, updatedUserProfile);
    }
}
