package com.example.EnumProject.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String cohortName;
    private String cohortDescription;
    private String cohortStatus;
    private String message;
    private String token;
}
