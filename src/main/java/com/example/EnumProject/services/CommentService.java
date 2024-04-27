package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;

public interface CommentService {
    Comment addCommentToPost(CommentRequest comment);
    Comment getCommentById(Long id);
    UpdateResponse editComment(Comment comment, Long id);
    DeleteResponse deleteComment(Long id);

    CommentResponse addCommentToComment(Comment comment, Long id);

}
