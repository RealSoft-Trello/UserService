package com.example.realsoft.user_service.service;

import com.example.realsoft.user_service.model.AuthenticationResponse;
import com.example.realsoft.user_service.model.UserLogin;
import com.example.realsoft.user_service.model.UserRequest;

public interface AuthenticationService {
    AuthenticationResponse registerUser(UserRequest userRequest);
    AuthenticationResponse authenticateUser(UserLogin userLogin);
}
