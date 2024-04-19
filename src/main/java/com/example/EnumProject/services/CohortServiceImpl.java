package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Cohort;
import com.example.EnumProject.data.model.Role;
import com.example.EnumProject.data.repository.CohortRepository;
import com.example.EnumProject.dtos.register.CreateCohortRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortServiceImpl implements CohortService {
    private final CohortRepository cohortRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public AuthResponse createCohort(CreateCohortRequest cohortRequest) {
        var user = Cohort.builder()
                .name(cohortRequest.getCohortName())
                .description(cohortRequest.getCohortDescription())
                .email(cohortRequest.getEmail())
                .password(passwordEncoder.encode(cohortRequest.getPassword()))
                .role(Role.COHORT)
                .build();





        return null;
    }
}
