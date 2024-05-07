package com.example.EnumProject.dtos.response;

import com.example.EnumProject.data.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddInstructorResponse {

    private String instructorName;
    private String instructorEmail;
    private String organization;
    private Status status;
    private String course;
    private LocalDateTime date;
    private String message;
}
