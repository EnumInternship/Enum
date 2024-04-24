package com.example.EnumProject.dtos.request;

import com.example.EnumProject.data.model.Status;
import lombok.Data;

@Data
public class AddInstructorRequest {
    private String instructorName;
    private String organization;
    private String instructorEmail;
    private Status status;
    private String course;
    private String date;
}
