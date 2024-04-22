package com.example.EnumProject.data.repository;

import com.example.EnumProject.data.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findByName(String name);

    Optional<Cohort> findByEmail(String email);


}
