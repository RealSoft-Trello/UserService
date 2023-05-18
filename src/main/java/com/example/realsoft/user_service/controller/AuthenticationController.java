package com.example.realsoft.user_service.controller;

import com.example.realsoft.user_service.model.AuthenticationResponse;
import com.example.realsoft.user_service.model.UserLogin;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realsoft/trello/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register" )
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(authenticationService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login" )
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(authenticationService.authenticateUser(userLogin), HttpStatus.CREATED);
    }
}
