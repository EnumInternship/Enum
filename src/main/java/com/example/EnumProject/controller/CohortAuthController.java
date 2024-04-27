package com.example.EnumProject.controller;


import com.example.EnumProject.dtos.request.CreateCohortRequest;
import com.example.EnumProject.dtos.request.LoginCohortRequest;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.dtos.response.AuthResponse;
import com.example.EnumProject.services.CohortService;
import com.example.EnumProject.services.UserService;
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
    private final UserService userService;

    @PostMapping("/createCohort")
    public ApiResponse<?> createCohort(
            @RequestBody CreateCohortRequest request
    ) {
        return cohortService.createCohort(request);
    }




}