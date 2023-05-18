package com.example.realsoft.user_service.service;

import com.example.realsoft.user_service.entity.Role;
import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating users by hand!");
        User user1 = new User(null, "Ozod", "Tagoev",
                passwordEncoder.encode("hello7166"), "ozod@gmail.com", Role.USER);

        User user2 = new User(null, "Jimmy", "Fallen",
                passwordEncoder.encode("jimMy187"), "Jimmy@aol.org", Role.ADMIN);

        User user3 = new User(null, "Khurshid", "Normurodov",
                passwordEncoder.encode("Kamano&@98"), "kamano@realsoft.uz", Role.MANAGER);

        User user4 = new User(null, "Rashidjon", "Ismoilov",
                passwordEncoder.encode("soykechar98"), "rashidjon@gmail.com", Role.USER);

        User user5 = new User(null, "Azizjon", "Ergashev",
                passwordEncoder.encode("azizchik#02"), "azizchik02@gmail.com", Role.USER);

        log.info("Adding created users to DB!");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        userRepository.saveAll(users);
    }
}
