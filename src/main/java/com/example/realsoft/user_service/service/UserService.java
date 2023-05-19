package com.example.realsoft.user_service.service;

import com.example.realsoft.user_service.model.Board;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.model.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long userId) throws ResourceNotFound;
    void deleteUser(Long userId) throws ResourceNotFound;
    UserResponse updateUser(Long userId, UserRequest userRequest) throws ResourceNotFound;
    List<UserResponse> getAllUsers();

    List<Board> getBoardsById(Long userId) throws ResourceNotFound;
}
