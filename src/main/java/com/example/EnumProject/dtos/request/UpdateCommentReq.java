package com.example.EnumProject.dtos.request;


import lombok.Data;

@Data

public class UpdateCommentReq {
    private Long id;
    private String content;
}
