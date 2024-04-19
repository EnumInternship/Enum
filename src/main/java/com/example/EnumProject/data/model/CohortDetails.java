package com.example.EnumProject.data.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CohortDetails {
    private String email;
    private String password;
}
