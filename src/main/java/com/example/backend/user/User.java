package com.example.backend.user;

import com.example.backend.thought.Thought;
import com.example.backend.user_profile.UserProfile;
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
    @Column(name = "id")
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "thought_fid", referencedColumnName = "id")
    private Set<Thought> thoughts = new HashSet<Thought>();

    public User(String name, String lastName) {
        this.nick = name;
        this.email = lastName;
    }
}
