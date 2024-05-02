package com.example.EnumProject.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddPostRequest {
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
