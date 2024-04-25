package com.example.EnumProject.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String instructorName;
    private String organization;
    private String instructorEmail;
    private String token;
    private Status status;
    private String course;
    private String date;

}
