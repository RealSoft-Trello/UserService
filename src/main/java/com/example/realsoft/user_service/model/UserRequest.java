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
    @Size(max = 20)
    private String firstname;
    @Size(max = 20)
    private String lastname;
    @NotBlank(message = "Password should not be blank!")
    private String password;
    @NotBlank(message = "Email can't be empty!")
    @Email
    private String email;
}
