package com.example.realsoft.user_service.service;

import com.example.realsoft.user_service.entity.Role;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.UserLogin;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.model.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserByEmail(UserLogin userLogin) throws ResourceNotFound;
    UserResponse createUser(UserRequest userRequest) throws ResourceNotFound;
    UserResponse getUserById(Long userId) throws ResourceNotFound;
    void deleteUser(Long userId) throws ResourceNotFound;
    UserResponse updateUser(Long userId, UserRequest userRequest) throws ResourceNotFound;
    List<UserResponse> getAllUsers();
    Role saveRole(Role role);
}
