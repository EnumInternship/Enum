package com.example.EnumProject.dtos.response;

import com.example.EnumProject.data.model.Status;
import lombok.Data;

@Data
public class AddInstructorResponse {

    private String instructorName;
    private String instructorEmail;
    private String organization;
    private Status status;
    private String course;
    private String date;
    private String message;
}
