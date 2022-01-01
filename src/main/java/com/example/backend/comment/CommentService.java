package com.example.backend.comment;

import com.example.backend.thought.Thought;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Thought> getComments(Long thoughtId) {
        return commentRepository.findCommentsByThoughtId(thoughtId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
