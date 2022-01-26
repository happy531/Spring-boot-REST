package com.example.backend.user_profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Optional<UserProfile> getProfile(String id) {
        return userProfileRepository.findById(id);
    }

    public void saveUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public void updateUserProfile(String userID, UserProfile updatedUserProfile) {
        updatedUserProfile.setUserId(userID);
        userProfileRepository.save(updatedUserProfile);
    }
}
