package com.example.EnumProject.controller;

import com.example.EnumProject.data.model.Instructor;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.*;
import com.example.EnumProject.dtos.response.AddInstructorResponse;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.services.CohortService;
import com.example.EnumProject.services.InstructorService;
import com.example.EnumProject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final InstructorService instructorService;
    private final CohortService cohortService;

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            return ApiResponse.success(userService.signUp(registerUserRequest),
                    "Sign up successful");
        }catch (Exception e){
            return ApiResponse.error("Sign up failed");
        }
    }

    @PostMapping("/authenticateUser")
    public ApiResponse<?> authenticateUser(@RequestBody LoginUserRequest loginUserRequest){
        try {
            return ApiResponse.success(userService.login(loginUserRequest),
                    "Login successful");
        }catch (Exception e){
            return ApiResponse.error("Login failed");
        }
    }

    @PostMapping("/createCohort")
    public ApiResponse<?> createCohort(
            @RequestBody CreateCohortRequest request
    ) {
        try {
            return ApiResponse.success(cohortService.createCohort(request),
                    "Cohort Successfully created");
        }catch (Exception e){
            return ApiResponse.error("Cohort creation failed");
        }

    }



    @PostMapping("/instructor")
    public ApiResponse<?> addInstructor(@RequestBody AddInstructorRequest addInstructorRequest) {
        try {
            return ApiResponse.success(instructorService.addInstructor(addInstructorRequest),
                    "Instructor Added successfully");
        }
        catch (Exception e){
            return ApiResponse.error("Instructor creation failed");
        }

    }

    @PostMapping("/verification")
    public ApiResponse<Instructor> verifyInstructor(@RequestBody String token) {
        try {
            return ApiResponse.success(instructorService.verifyInvitedInstructor(token),
                    "Instructor Verified successfully");
        }
        catch (Exception e){
            return ApiResponse.error("Instructor verification failed");
        }

    }
}
