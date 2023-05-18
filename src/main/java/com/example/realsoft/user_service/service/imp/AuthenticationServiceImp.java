package com.example.realsoft.user_service.service.imp;

import com.example.realsoft.user_service.configuration.JwtService;
import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.entity.Role;
import com.example.realsoft.user_service.exception.APIException;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.AuthenticationResponse;
import com.example.realsoft.user_service.model.UserLogin;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.repository.UserRepository;
import com.example.realsoft.user_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            log.info("You entered already existed email {}", userRequest.getEmail());
            throw new APIException(HttpStatus.BAD_REQUEST, "User is already exists in DB!");
        }
        var user = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        log.info("User {} saved to DB successfully!", userRequest.getEmail());
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticateUser(UserLogin userLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        var user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> {
                    try {
                        log.info("There is no such a User {} in DB", userLogin.getEmail());
                        throw new ResourceNotFound("User", "Email", userLogin.getEmail());
                    } catch (ResourceNotFound e) {
                        throw new RuntimeException(e);
                    }
                });
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
