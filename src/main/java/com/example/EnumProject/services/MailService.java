package com.example.EnumProject.services;

import com.example.EnumProject.dtos.request.InviteInstructorRequest;
import com.example.EnumProject.dtos.response.InviteResponse;

public interface MailService {
    InviteResponse sendMail(InviteInstructorRequest inviteRequest);
}
