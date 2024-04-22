package com.example.EnumProject.controller;


import com.example.EnumProject.dtos.request.CreateCohortRequest;
import com.example.EnumProject.dtos.request.LoginCohortRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
import com.example.EnumProject.services.CohortService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class CohortAuthController {
    private final CohortService cohortService;

    @PostMapping("/createCohort")
    public ResponseEntity<AuthResponse> createCohort(
            @RequestBody CreateCohortRequest request
    ) {
        return ResponseEntity.ok(cohortService.createCohort(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> register(

            @RequestBody LoginCohortRequest request
    ) {
        log.info("Inside authenticate method");
        return ResponseEntity.ok(cohortService.authenticate(request));
    }

}