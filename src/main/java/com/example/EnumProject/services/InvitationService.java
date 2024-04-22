package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Invitation;
import com.example.EnumProject.dtos.response.InvitationResponse;

public interface InvitationService {
    InvitationResponse createInvitation(Invitation invitation);
}
