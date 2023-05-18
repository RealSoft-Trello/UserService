package com.example.realsoft.user_service.model;

import com.example.realsoft.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Role role;
}
