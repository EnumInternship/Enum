package com.example.EnumProject.exception;

public class InstructorNotFound extends RuntimeException {
    public InstructorNotFound(String message) {
        super(message);
    }
}
