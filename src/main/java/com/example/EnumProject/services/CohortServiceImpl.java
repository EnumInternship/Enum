package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Cohort;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.data.repository.CohortRepository;
import com.example.EnumProject.data.repository.UserRepository;
import com.example.EnumProject.dtos.request.CreateCohortRequest;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.exception.UserNotFoundException;
import com.example.EnumProject.security.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CohortServiceImpl implements CohortService {
    private final UserRepository userRepository;
    private final CohortRepository cohortRepository;


    public ApiResponse<?> createCohort(CreateCohortRequest cohortRequest) {
        User cohortCreator = userRepository.findByUsername(cohortRequest.getCreatorUsername()).orElseThrow(()->
        new UserNotFoundException("User not found"));

        Cohort cohort = Cohort.builder()
                .name(cohortRequest.getCohortName())
                .description(cohortRequest.getCohortDescription())
                .startDate(cohortRequest.getStartDate())
                .endDate(cohortRequest.getEndDate())
                .creator(cohortCreator)
                .build();
        cohortRepository.save(cohort);
       return ApiResponse.success(cohort, "successfully");


    }

}
