package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;

public interface PostService {

    Post addPost(AddPostRequest addPostRequest);

    Comment addComment(CommentRequest commentRequest);
}
