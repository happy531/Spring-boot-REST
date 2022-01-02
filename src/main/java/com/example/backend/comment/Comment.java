package com.example.backend.comment;

import com.example.backend.thought.Thought;
import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"thought"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "time_added")
    private LocalTime timeAdded;

    @ManyToOne
    @JoinColumn(name = "thought_id")
//    @JsonIgnoreProperties("thought")
    private Thought thought;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("comments")
    private User user;

}
