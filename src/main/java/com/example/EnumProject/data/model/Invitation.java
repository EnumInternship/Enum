package com.example.EnumProject.data.model;

import lombok.Data;

@Data
public class Invitation {
    private Instructor instructor;
    private Status status;
    private String date;
    private boolean isInviteAccepted;


}
