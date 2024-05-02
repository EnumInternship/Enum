package com.example.EnumProject.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpResponse {
    private String email;
    private String message;
    private String token;
}
