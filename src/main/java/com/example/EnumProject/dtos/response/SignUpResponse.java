package com.example.EnumProject.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SignUpResponse {
    private String email;
    private String message;
    private String token;
}
