package com.example.EnumProject.data.repository;

import com.example.EnumProject.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByInstructorEmail(String instructorEmail);

    Optional<Instructor> findByToken(String token);
}
