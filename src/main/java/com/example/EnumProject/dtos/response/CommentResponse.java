package com.example.EnumProject.dtos.response;

import lombok.Data;

@Data
public class CommentResponse<T> {
    private String message;
    private T data;
}
