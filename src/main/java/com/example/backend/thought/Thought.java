package com.example.backend.thought;

import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "thoughts")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"user"})
public class Thought {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thought_id")
    private Long id;

    @Column(
            name = "thought",
            nullable = false,
            columnDefinition = "TEXT")
    private String thought;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
