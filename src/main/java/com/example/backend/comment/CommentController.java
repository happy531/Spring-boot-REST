package com.example.backend.comment;

import com.example.backend.thought.Thought;
import com.example.backend.thought.ThoughtService;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/thought/{thoughtId}/comment")
public class CommentController {

    private final CommentService commentService;
    private final ThoughtService thoughtService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, ThoughtService thoughtService, UserService userService) {
        this.commentService = commentService;
        this.thoughtService = thoughtService;
        this.userService = userService;
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable("thoughtId") Long thoughtId) {
        return commentService.getComments(thoughtId);
    }

    @PostMapping(path = "{userId}")
    public void addComment(@PathVariable("thoughtId") Long thoughtId,
                           @PathVariable("userId") Long userId,
                           @RequestBody Comment comment) {
        Optional<Thought> thoughtOptional = thoughtService.getThought(thoughtId);
        if (thoughtOptional.isEmpty()) {
            throw new IllegalStateException("This thought does not exist");
        }
        Thought thought = thoughtOptional.get();
        Set<Comment> comments = thought.getComments();
        comments.add(comment);
        thought.setComments(comments);
        comment.setThought(thought);

        Optional<User> userOptional = userService.getUser(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("This user does not exist");
        }
        User user = userOptional.get();
        comment.setUser(user);

        commentService.addComment(comment);
    }

    @DeleteMapping(path = "{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId,
                              @PathVariable Long thoughtId) {
        Thought thought = thoughtService.getThought(thoughtId).orElseThrow(() -> new IllegalStateException("thought does not exist"));
        Set<Comment> comments = thought.getComments();
        comments.removeIf(obj -> obj.getCommentId() == commentId);
        thought.setComments(comments);
        commentService.deleteComment(commentId);
    }

}
