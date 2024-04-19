package com.example.EnumProject.dtos.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String cohortName;
    private String cohortDescription;
    private String cohortStatus;
    private String message;
}
