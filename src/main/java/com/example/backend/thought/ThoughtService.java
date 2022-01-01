package com.example.backend.thought;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThoughtService {
    private final ThoughtRepository thoughtRepository;

    @Autowired
    public ThoughtService(ThoughtRepository thoughtRepository) {
        this.thoughtRepository = thoughtRepository;
    }

    public List<Thought> getThoughts() {
        return thoughtRepository.findAll();
    }

    public Optional<Thought> getThought(Long thoughtId) {
        return thoughtRepository.findById(thoughtId);
    }

    public void saveThought(Thought thought) {
        thoughtRepository.save(thought);
    }

    public void deleteThought(Long thoughtID) {
        thoughtRepository.deleteById(thoughtID);
    }


}
