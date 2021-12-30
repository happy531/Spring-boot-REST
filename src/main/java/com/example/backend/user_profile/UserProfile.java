package com.example.backend.user_profile;

import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"user", "id"})
@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private int age;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserProfile(Gender gender, int age) {
        this.gender = gender;
        this.age = age;
    }
}
