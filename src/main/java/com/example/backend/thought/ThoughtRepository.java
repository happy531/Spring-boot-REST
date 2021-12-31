package com.example.backend.thought;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThoughtRepository extends JpaRepository<Thought, Long> {
}
