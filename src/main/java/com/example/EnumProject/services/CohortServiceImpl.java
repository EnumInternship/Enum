package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Cohort;
import com.example.EnumProject.data.model.Role;
import com.example.EnumProject.data.repository.CohortRepository;
import com.example.EnumProject.dtos.request.CreateCohortRequest;
import com.example.EnumProject.dtos.request.LoginCohortRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CohortServiceImpl implements CohortService {
    private final CohortRepository cohortRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;


    public AuthResponse createCohort(CreateCohortRequest cohortRequest) {
        var user = Cohort.builder()
                .name(cohortRequest.getCohortName())
                .description(cohortRequest.getCohortDescription())
                .email(cohortRequest.getEmail())
                .password(passwordEncoder.encode(cohortRequest.getPassword()))
                .role(Role.COHORT)
                .build();
        cohortRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .message("Successfully created cohort")
                .cohortDescription(cohortRequest.getCohortDescription())
                .build();

    }

//    @Override
//    public AuthResponse authenticate(LoginCohortRequest loginRequest) {
//        return null;
//    }

    public AuthResponse authenticate(LoginCohortRequest request) {
//        log.info("Authenticating user {}", request.getEmail());
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        log.info("Authenticated user {}", request.getEmail());
//        var user = cohortRepository.findByEmail(request.getEmail()).orElse(null);
//        log.info("Found user {}", user);
//        var jwtToken = jwtService.generateToken(user);
//        log.info("Successfully authenticated user {}", request.getEmail());
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .build();
        try {
            log.info("Authenticating user {}", request.getEmail());
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            log.info("User {} authenticated successfully", request.getEmail());

            var user = cohortRepository.findByEmail(request.getEmail()).orElse(null);
            if (user == null) {
                log.error("User {} not found in the database", request.getEmail());
                return null;
            }
            log.info("Found user {}", user);

            var jwtToken = jwtService.generateToken(user);
            log.info("Successfully authenticated user {}", request.getEmail());

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user {}", request.getEmail(), e);
            return null;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        return null;
    }

}
