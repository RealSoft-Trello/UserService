package com.example.realsoft.user_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username can not be blank!")
    private String username;
    @Size(max = 40)
    private String name;
    @NotBlank(message = "Password should not be blank!")
    private String password;
    @NotBlank(message = "Email can't be empty!")
    @Email
    private String email;
    @NotBlank(message = "You did not enter the role for this user!")
    private String roleName;
}
