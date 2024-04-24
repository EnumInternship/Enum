package com.example.EnumProject.services;

import com.example.EnumProject.dtos.request.AddInstructorRequest;
import com.example.EnumProject.dtos.request.InviteInstructorRequest;
import com.example.EnumProject.dtos.response.AddInstructorResponse;
import com.example.EnumProject.dtos.response.InviteResponse;

public interface InstructorService {

    AddInstructorResponse addInstructor(AddInstructorRequest addInstructorRequest);

    InviteResponse inviteInstructor(InviteInstructorRequest inviteInstructor);
}

