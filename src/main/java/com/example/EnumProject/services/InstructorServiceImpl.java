package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Instructor;
import com.example.EnumProject.data.model.Status;
import com.example.EnumProject.data.repository.InstructorRepository;
import com.example.EnumProject.dtos.request.AddInstructorRequest;
import com.example.EnumProject.dtos.response.AddInstructorResponse;
import com.example.EnumProject.exception.DuplicateInstructorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public AddInstructorResponse addInstructor(AddInstructorRequest addInstructorRequest) {
        validateInstructor(addInstructorRequest);

        Instructor newInstructor = mapInstructor(addInstructorRequest);
        instructorRepository.save(newInstructor);

        AddInstructorResponse response = new AddInstructorResponse();
        response.setInstructorName(newInstructor.getInstructorName());
        response.setInstructorEmail(newInstructor.getInstructorEmail());
        response.setOrganization(newInstructor.getOrganization());
        response.setCourse(newInstructor.getCourse());
        response.setDate(newInstructor.getDate());
        response.setMessage("Instructor added successfully.");

        return response;
    }

    private static Instructor mapInstructor(AddInstructorRequest addInstructorRequest) {
        Instructor newInstructor = new Instructor();
        newInstructor.setInstructorName(addInstructorRequest.getInstructorName());
        newInstructor.setInstructorEmail(addInstructorRequest.getInstructorEmail());
        newInstructor.setOrganization(addInstructorRequest.getOrganization());
        newInstructor.setCourse(addInstructorRequest.getCourse());
        newInstructor.setStatus(Status.PENDING);
        newInstructor.setDate(addInstructorRequest.getDate());
        return newInstructor;
    }

    private void validateInstructor(AddInstructorRequest addInstructorRequest) {
        Optional<Instructor> newInstructor = instructorRepository
                .findByInstructorEmail(addInstructorRequest.getInstructorEmail());

        if (newInstructor.isPresent())
            throw new DuplicateInstructorException("Instructor exists");
    }

}
