package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Instructor;
import com.example.EnumProject.data.model.Recipient;
import com.example.EnumProject.data.model.Sender;
import com.example.EnumProject.data.model.Status;
import com.example.EnumProject.data.repository.InstructorRepository;
import com.example.EnumProject.dtos.request.AddInstructorRequest;
import com.example.EnumProject.dtos.request.InviteInstructorRequest;
import com.example.EnumProject.dtos.response.AddInstructorResponse;
import com.example.EnumProject.dtos.response.InviteResponse;
import com.example.EnumProject.exception.DuplicateInstructorException;
import com.example.EnumProject.exception.IncorrectTokenException;
import com.example.EnumProject.exception.InstructorNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private MailService mailService;

    @Override
    public AddInstructorResponse addInstructor(AddInstructorRequest addInstructorRequest) {
        validateInstructor(addInstructorRequest);

        Instructor newInstructor = mapInstructor(addInstructorRequest);
        instructorRepository.save(newInstructor);

        mailService.sendMail(buildMailRequest(newInstructor));

        AddInstructorResponse response = new AddInstructorResponse();
        response.setInstructorName(newInstructor.getInstructorName());
        response.setInstructorEmail(newInstructor.getInstructorEmail());
        response.setOrganization(newInstructor.getOrganization());
        response.setCourse(newInstructor.getCourse());
        response.setDate(LocalDateTime.from(LocalDateTime.now()));
        response.setMessage("Instructor added successfully.");

        return response;
    }

    @Override
    public Instructor verifyInvitedInstructor(String token) {
        Optional<Instructor> instructorOptional = instructorRepository.findByToken(token);

        if (instructorOptional.isEmpty())
            throw new InstructorNotFound("The token is incorrect");

        Instructor instructor = instructorOptional.get();

        String generatedToken = instructor.getToken();

        if (token.equals(generatedToken)) {
            instructor.setStatus(Status.ACTIVE);
            instructorRepository.save(instructor);
            return instructor;
        }
        throw new IncorrectTokenException("The token is incorrect");
    }

    @Override
    public InviteResponse inviteInstructor(InviteInstructorRequest inviteInstructor) {
        return null;
    }

    private static Instructor mapInstructor(AddInstructorRequest addInstructorRequest) {
        Instructor newInstructor = new Instructor();
        newInstructor.setInstructorName(addInstructorRequest.getInstructorName());
        newInstructor.setInstructorEmail(addInstructorRequest.getInstructorEmail());
        newInstructor.setOrganization(addInstructorRequest.getOrganization());
        newInstructor.setCourse(addInstructorRequest.getCourse());
        newInstructor.setStatus(Status.PENDING);
        newInstructor.setDate(LocalDateTime.from(LocalDateTime.now()));
        return newInstructor;
    }

    private void validateInstructor(AddInstructorRequest addInstructorRequest) {
        Optional<Instructor> newInstructor = instructorRepository
                .findByInstructorEmail(addInstructorRequest.getInstructorEmail());

        if (newInstructor.isPresent())
            throw new DuplicateInstructorException("Instructor exists");
    }

    private InviteInstructorRequest buildMailRequest(Instructor instructor){
        String invitationToken = generateInviteToken(instructor);
        instructor.setToken(invitationToken);
        instructorRepository.save(instructor);

        InviteInstructorRequest mailRequest = new InviteInstructorRequest();
        Sender sender = new Sender("Enum Africa", "gadaphi001@gmail.com");
        List<Recipient> recipients = List.of(
                new Recipient(instructor.getInstructorEmail(), instructor.getInstructorEmail())
        );

        String htmlContent = "<p>Hello " + instructor.getInstructorName() + ",</p>"
                + "<p>Welcome to our learning platform!</p>"
                + "<p>Here's your 8 digit code " + invitationToken + " </p>"
                + "<p>Best regards,</p>"
                + "<p>Enum Africa Team</p>";

        mailRequest.setSubject("Invitation");
        mailRequest.setHtmlContent(htmlContent);
        mailRequest.setSender(sender);
        mailRequest.setRecipients(recipients);
        return mailRequest;
    }

    private static String generateInviteToken(Instructor instructor) {
        StringBuilder token = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8;

        String email = instructor.getInstructorEmail();
        token.append(email.hashCode());

        Random random = new Random();

        for (int index = 0; index < length; index++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        return token.toString();
    }

}
