package com.example.realsoft.user_service.controller;

import com.example.realsoft.user_service.model.BoardDto;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.CommentDto;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.model.UserResponse;
import com.example.realsoft.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/realsoft/trello/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") Long userId) throws ResourceNotFound {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody UserRequest userRequest) throws ResourceNotFound {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long userId) throws ResourceNotFound {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/{id}/boards" )
    public ResponseEntity<List<BoardDto>> getBoardsById(@PathVariable(name = "id" ) Long userId) throws ResourceNotFound {
        return ResponseEntity.ok(userService.getBoardsById(userId));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsById(@PathVariable(name = "id") Long userId) throws ResourceNotFound {
        return ResponseEntity.ok(userService.getCommentsById(userId));
    }
}
