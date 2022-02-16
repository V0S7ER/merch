package com.cod.merch.model.DTO.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class ContestRequest {
    private String name;
    private Date date;

    private String admin_email;
    private String admin_password;
}
