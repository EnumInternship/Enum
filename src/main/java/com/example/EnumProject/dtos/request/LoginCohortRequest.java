package com.example.EnumProject.dtos.request;

import lombok.Data;

@Data
public class LoginCohortRequest {
    private String name;
    private String email;
    private String password;
}
