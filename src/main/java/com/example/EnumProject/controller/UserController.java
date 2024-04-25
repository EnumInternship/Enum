package com.example.EnumProject.controller;

import com.example.EnumProject.data.model.Instructor;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.*;
import com.example.EnumProject.dtos.response.AddInstructorResponse;
import com.example.EnumProject.dtos.response.AuthResponse;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.signUp(registerUserRequest));
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<AuthResponse> authenticateUser(
            @RequestBody LoginUserRequest loginUserRequest){
        return ResponseEntity.ok(userService.login(loginUserRequest));
    }

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody AddPostRequest request) {
        return ResponseEntity.ok(userService.addPost(request));
    }

    @PostMapping("/instructor")
    public ResponseEntity<AddInstructorResponse> addInstructor(@RequestBody AddInstructorRequest addInstructorRequest) {
        return ResponseEntity.ok(instructorService.addInstructor(addInstructorRequest));
    }

    @PostMapping("/verification")
    public ResponseEntity<Instructor> verifyInstructor(@RequestBody String token) {
        return ResponseEntity.ok(instructorService.vrifyInvitedInstructor(token));
    }
}
