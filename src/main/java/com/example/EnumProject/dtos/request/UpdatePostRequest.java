package com.example.EnumProject.dtos.request;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Long postId;
    private String content;
    private String title;
    private String author;
}
