package com.example.backend.comment;

import com.example.backend.thought.Thought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select t from Thought t inner join fetch t.comments where t.thoughtId = :id")
    List<Thought> findCommentsByThoughtId(@Param("id") Long id);

}
