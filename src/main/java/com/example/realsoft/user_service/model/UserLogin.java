package com.example.realsoft.user_service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserLogin {
    @NotBlank(message = "Please enter the username!")
    private String username;
    @NotBlank(message = "Please enter the username!")
    private String password;
}
