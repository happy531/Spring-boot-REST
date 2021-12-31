package com.example.backend.thought;

import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/thought")
public class ThoughtController {

    private final ThoughtService thoughtService;
    private final UserService userService;

    @Autowired
    public ThoughtController(ThoughtService thoughtService, UserService userService) {
        this.thoughtService = thoughtService;
        this.userService = userService;
    }

    @GetMapping
    public List<Thought> getThoughts() {
        return thoughtService.getThoughts();
    }

    @PostMapping(path = "{userID}")
    public void saveThought(
            @PathVariable("userID") Long userID,
            @RequestBody Thought thought
    ) {
        Optional<User> userOptional = userService.getUser(userID);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("This profile does not exists");
        }
        User user = userOptional.get();
        Set<Thought> thoughts = user.getThoughts();
        thoughts.add(thought);
        user.setThoughts(thoughts);

        thought.setUser(user);
        thoughtService.saveThought(thought);
    }
}
