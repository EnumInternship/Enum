package com.example.EnumProject.dtos.request;

import com.example.EnumProject.data.model.Recipient;
import com.example.EnumProject.data.model.Sender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SendMailRequest {
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    private String subject;
    private String htmlContent;
}
