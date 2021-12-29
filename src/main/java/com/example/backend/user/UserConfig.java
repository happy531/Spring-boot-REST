package com.example.backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User andrew = new User(
                    "Andrew",
                    "andrew@gmail.com"
            );
            User jacob = new User(
                    "Jacob",
                    "jacob@gmail.com"
            );

            userRepository.saveAll(List.of(andrew, jacob));
        };
    }
}
