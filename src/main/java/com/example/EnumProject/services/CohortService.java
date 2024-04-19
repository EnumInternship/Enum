package com.example.EnumProject.services;

import com.example.EnumProject.dtos.register.CreateCohortRequest;
import com.example.EnumProject.dtos.response.AuthResponse;

public interface CohortService {
    AuthResponse createCohort(CreateCohortRequest registerRequest);
}
