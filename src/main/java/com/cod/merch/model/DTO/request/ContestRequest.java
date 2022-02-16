package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
public class ContestRequest {
    @Nullable
    private String name;
    @Nullable
    private Date date;

    private String admin_email;
    private String admin_password;
}
