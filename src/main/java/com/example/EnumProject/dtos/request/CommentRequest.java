package com.example.EnumProject.dtos.request;

import com.example.EnumProject.data.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequest {
    private Long id;
    private String content;
    private String comment;
    private String author;
    private LocalDateTime createdAt;
    private String postId;
}
