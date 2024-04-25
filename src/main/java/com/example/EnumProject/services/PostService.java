package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;

public interface PostService {

    Post addPost(AddPostRequest addPostRequest);

    CommentResponse addComment(CommentRequest commentRequest);

    UpdateResponse updatePost(Post post, Long postId);

    DeleteResponse deletePost(Long postId);
    UpdateResponse editComment(Comment comment, Long id);
}
