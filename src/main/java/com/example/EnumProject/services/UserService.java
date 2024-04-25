package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.LoginUserRequest;
import com.example.EnumProject.dtos.request.RegisterUserRequest;
import com.example.EnumProject.dtos.response.AuthResponse;

public interface UserService {
    AuthResponse signUp(RegisterUserRequest registerUser);
    AuthResponse login(LoginUserRequest request);
    Post addPost(AddPostRequest addPostRequest);
}
