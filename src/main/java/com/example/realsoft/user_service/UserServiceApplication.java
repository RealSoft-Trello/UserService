package com.example.realsoft.user_service;

import com.example.realsoft.user_service.entity.Role;
import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.repository.UserRepository;
import com.example.realsoft.user_service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			User user1 = new User(null, "Ozod", "Tagoev",
					passwordEncoder.encode("hello7166"), "ozod.tagoev@gmail.com", Role.USER);

			User user2 = new User(null, "Jimmy", "Fallen",
					passwordEncoder.encode("jimMy187"), "Jimmy@aol.org", Role.ADMIN);

			User user3 = new User(null, "Khurshid", "Normurodov",
					passwordEncoder.encode("Kamano&@98"), "kamano@realsoft.uz", Role.MANAGER);

			User user4 = new User(null, "Rashidjon", "Ismoilov",
					passwordEncoder.encode("soykechar98"), "rashidjon@gmail.com", Role.USER);

			User user5 = new User(null, "Azizjon", "Ergashev",
					passwordEncoder.encode("azizchik#02"), "azizchik02@gmail.com", Role.USER);

			List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
			userRepository.saveAll(users);
		};
	}
}
