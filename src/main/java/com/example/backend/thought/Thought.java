package com.example.backend.thought;

import com.example.backend.comment.Comment;
import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "thoughts")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"comments"})
public class Thought {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thought_id")
    private Long thoughtId;

    @Column(
            name = "thought",
            nullable = false,
            columnDefinition = "TEXT")
    private String thought;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "time_added")
    private LocalTime timeAdded;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("thoughts")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "thought_id")
    private Set<Comment> comments = new HashSet<>();
}
