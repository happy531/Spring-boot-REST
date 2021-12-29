package com.example.backend.user_profile;

import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"user"})
@Entity
@Table(name = "user_profiles")
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

    public UserProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
