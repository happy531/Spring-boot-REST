package com.example.backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User andrew = new User(
                    "Andrew",
                    "andrew@gmail.com",
                    LocalDate.of(2001, Month.APRIL, 29)
            );
            User jacob = new User(
                    "Jacob",
                    "jacob@gmail.com",
                    LocalDate.of(1993, Month.JUNE, 29)
            );

            userRepository.saveAll(List.of(andrew, jacob));
        };
    }
}
