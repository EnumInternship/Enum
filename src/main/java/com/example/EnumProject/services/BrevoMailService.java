package com.example.EnumProject.services;

import com.example.EnumProject.config.AppConfig;
import com.example.EnumProject.dtos.request.InviteInstructorRequest;
import com.example.EnumProject.dtos.response.InviteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class BrevoMailService implements MailService{

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    @Override
    public InviteResponse sendMail(InviteInstructorRequest inviteRequest) {
        System.out.println(inviteRequest);
        HttpHeaders headers = addRequestHeaders();
        RequestEntity<InviteInstructorRequest> requestEntity = new RequestEntity<>(inviteRequest, headers, POST, URI.create(appConfig.getMailServiceUrl()));
        ResponseEntity<InviteResponse> mailResponse =restTemplate.postForEntity(appConfig.getMailServiceUrl(), requestEntity, InviteResponse.class);
        return buildSendMailResponse(mailResponse);
    }

    private static InviteResponse buildSendMailResponse(ResponseEntity<InviteResponse> mailResponse) {
        HttpStatusCode code = mailResponse.getStatusCode();
        var response = mailResponse.getBody();
        if (response==null) throw new RuntimeException("Mail Sending failed");
        response.setStatusCode(code.value());
        return response;
    }

    private HttpHeaders addRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.set("api-key", appConfig.getMailApiKey());
        return headers;
    }
}
