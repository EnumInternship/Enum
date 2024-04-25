package com.example.EnumProject.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InviteResponse {
    private int statusCode;
    private String messageId;
}
