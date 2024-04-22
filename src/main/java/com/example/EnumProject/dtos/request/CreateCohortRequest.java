package com.example.EnumProject.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCohortRequest {
    private String cohortName;
    private String cohortDescription;
    private String email;
    private String password;
    private String startDate;
    private String endDate;
}