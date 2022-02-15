package com.cod.merch.model.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class ContestRequest {
    private String name;
    private Date date;

    @JsonProperty("admin_email")
    private String adminEmail;
    @JsonProperty("admin_password")
    private String adminPassword;
}
