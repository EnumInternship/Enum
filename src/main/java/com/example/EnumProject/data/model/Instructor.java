package com.example.EnumProject.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String instructorName;
    private String organization;
    private String instructorEmail;
    private Status status;
    private String course;
    private String date;

}
