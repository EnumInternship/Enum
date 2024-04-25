package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.dtos.request.CommentRequest;

public interface CommentService {
    Comment addCommentToPost(CommentRequest comment);
    Comment getCommentById(Long id);
    Comment updateComment(Comment comment);

}
