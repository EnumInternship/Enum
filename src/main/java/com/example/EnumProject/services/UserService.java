package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.dtos.request.*;
import com.example.EnumProject.dtos.response.*;

public interface UserService {
    SignUpResponse signUp(RegisterUserRequest registerUser);
    LoginResponse login(LoginUserRequest request);
    Post addPost(AddPostRequest addPostRequest);
    ApiResponse<?> addComment(CommentRequest commentRequest);
    UpdateResponse updatePost(UpdatePostRequest postRequest);
    DeleteResponse deletePost(Long postId);

    ApiResponse<?> editComment(UpdateCommentReq comment);

}
