package com.example.backend.user;

import com.example.backend.thought.Thought;
import com.example.backend.user_profile.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(
            name = "nick",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nick;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("user")
    private Set<Thought> thoughts = new HashSet<Thought>();

    public User(String name, String lastName) {
        this.nick = name;
        this.email = lastName;
    }
}
