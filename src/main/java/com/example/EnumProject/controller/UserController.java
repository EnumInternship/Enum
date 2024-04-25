package com.example.EnumProject.controller;

import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.LoginUserRequest;
import com.example.EnumProject.dtos.request.RegisterUserRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
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
}
