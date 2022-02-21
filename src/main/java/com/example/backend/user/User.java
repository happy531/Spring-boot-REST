package com.example.backend.user;

import com.example.backend.comment.Comment;
import com.example.backend.thought.Thought;
import com.example.backend.user_profile.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"userProfile", "thoughts", "comments"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    private Instant created;
    private boolean enabled;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
////    @PrimaryKeyJoinColumn
//    private UserProfile userProfile;
//
//    @OneToMany(cascade = CascadeType.ALL)
////    @JoinColumn(name = "user_id")
//    private Set<Thought> thoughts = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL)
////    @JoinColumn(name = "user_id")
//    private Set<Comment> comments = new HashSet<>();

}
