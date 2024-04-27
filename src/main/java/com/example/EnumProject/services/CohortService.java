package com.example.EnumProject.services;

import com.example.EnumProject.dtos.request.CreateCohortRequest;
import com.example.EnumProject.dtos.request.LoginCohortRequest;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.dtos.response.AuthResponse;
import org.springframework.security.core.Authentication;

public interface CohortService {
    ApiResponse<?> createCohort(CreateCohortRequest registerRequest);

//    AuthResponse authenticate(LoginCohortRequest loginRequest);
//
//    Authentication authenticate(Authentication authentication);
}
