package com.example.backend.thought;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThoughtService {
    private final ThoughtRepository thoughtRepository;

    @Autowired
    public ThoughtService(ThoughtRepository thoughtRepository) {
        this.thoughtRepository = thoughtRepository;
    }

    public Page<Thought> getThoughts(Optional<Integer> page) {
        return thoughtRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        5,
                        Sort.Direction.ASC,
                        "thoughtId"));
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
