package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Instructor;
import com.example.EnumProject.data.model.Recipient;
import com.example.EnumProject.data.model.Sender;
import com.example.EnumProject.dtos.request.InviteInstructorRequest;
import com.example.EnumProject.dtos.request.SendMailRequest;
import com.example.EnumProject.dtos.response.InviteResponse;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AllArgsConstructor
class BrevoMailServiceTest {
    private BrevoMailService brevoMailService;



    @Test
    public void testSendEmail() {
        InviteInstructorRequest mailRequest = buildMailRequest();
        InviteResponse response = brevoMailService.sendMail(mailRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
    }
    private static InviteInstructorRequest buildMailRequest() {
        Instructor instructor = new Instructor();
        instructor.setInstructorName("Tomide");
        InviteInstructorRequest mailRequest = new InviteInstructorRequest();
        Sender sender = new Sender("StatVital", "gadaphi001@gmail.com");
        List<Recipient> recipients = List.of(
                new Recipient("Tomi", "oluwaseyitemitope69@gmail.com")
        );
        mailRequest.setSubject("Stat-Vital");
        mailRequest.setHtmlContent("<p>Hello " + instructor.getInstructorName() + ",</p>"
                + "<p>Welcome to our learning platform!</p>"
                + "<p>Here's your 8 digit code " + " </p>"
                + "<p>Best regards,</p>"
                + "<p>Enum Africa Team</p>");
        mailRequest.setSender(sender);
        mailRequest.setRecipients(recipients);
        return mailRequest;
    }

}