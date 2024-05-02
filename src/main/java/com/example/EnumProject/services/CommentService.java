package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.request.UpdateCommentReq;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;

public interface CommentService {
    Comment addCommentToPost(CommentRequest comment);
    ApiResponse<?> updateComment(CommentRequest comment);
    Comment getCommentById(Long id);
    ApiResponse<?> editComment(UpdateCommentReq commentRequest);
    DeleteResponse deleteComment(Long id);

    CommentResponse addCommentToComment(Comment comment, Long id);

}
