package com.example.EnumProject.dtos.request;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String email;
}
