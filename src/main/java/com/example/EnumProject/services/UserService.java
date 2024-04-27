package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.request.LoginUserRequest;
import com.example.EnumProject.dtos.request.RegisterUserRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;

public interface UserService {
    AuthResponse signUp(RegisterUserRequest registerUser);
    AuthResponse login(LoginUserRequest request);
    Post addPost(AddPostRequest addPostRequest);
    CommentResponse addComment(CommentRequest commentRequest);
    UpdateResponse updatePost(Post post, Long postId);
    DeleteResponse deletePost(Long postId);

}
