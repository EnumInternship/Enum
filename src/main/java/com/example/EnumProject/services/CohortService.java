package com.example.EnumProject.services;

import com.example.EnumProject.dtos.register.CohortRegisterRequest;
import com.example.EnumProject.dtos.response.AuthResponse;

public interface CohortService {
    AuthResponse register(CohortRegisterRequest registerRequest);
}
