package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.email = :#{#email}")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT user FROM User user WHERE user.nick = :#{#nick}")
    User findUserByNick(String nick);
}
