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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private MailService mailService;

    @Override
    public AddInstructorResponse addInstructor(AddInstructorRequest addInstructorRequest) {
        validateInstructor(addInstructorRequest);

        Instructor newInstructor = mapInstructor(addInstructorRequest);
        instructorRepository.save(newInstructor);

        String email = newInstructor.getInstructorEmail();
        mailService.sendMail(buildMailRequest(email));

        AddInstructorResponse response = new AddInstructorResponse();
        response.setInstructorName(newInstructor.getInstructorName());
        response.setInstructorEmail(newInstructor.getInstructorEmail());
        response.setOrganization(newInstructor.getOrganization());
        response.setCourse(newInstructor.getCourse());
        response.setDate(newInstructor.getDate());
        response.setMessage("Instructor added successfully.");

        return response;
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
        newInstructor.setDate(addInstructorRequest.getDate());
        return newInstructor;
    }

    private void validateInstructor(AddInstructorRequest addInstructorRequest) {
        Optional<Instructor> newInstructor = instructorRepository
                .findByInstructorEmail(addInstructorRequest.getInstructorEmail());

        if (newInstructor.isPresent())
            throw new DuplicateInstructorException("Instructor exists");
    }

    private static InviteInstructorRequest buildMailRequest(String email){
        InviteInstructorRequest mailRequest = new InviteInstructorRequest();
        Sender sender = new Sender("Enum Africa", "instructor@email.com");
        List<Recipient> recipients = List.of(
                new Recipient(email, email)
        );

        String name = "";
        for (Recipient recipient : recipients) {
            name = recipient.getInstructorName();
        }

        String invitationToken = generateInviteToken();

        String htmlContent = "<p>Hello " + name + ",</p>"
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

    private static String generateInviteToken() {
        StringBuilder token = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        Random random = new Random();
        for (int index = 0; index < length; index++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        return token.toString();
    }

}
