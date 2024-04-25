package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.repository.CommentRepository;
import com.example.EnumProject.dtos.request.CommentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment addCommentToPost(CommentRequest commentRequest) {
      var comment = Comment.builder()
              .author(commentRequest.getAuthor())
              .content(commentRequest.getContent())
              .build();
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return null;
    }
}
