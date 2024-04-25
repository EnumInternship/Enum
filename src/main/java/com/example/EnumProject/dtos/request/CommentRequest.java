package com.example.EnumProject.dtos.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private String author;
}
