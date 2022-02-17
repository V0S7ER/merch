package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ContestRequest {
    @Nullable
    private String name;
    @Nullable
    private String date; // dd.MM.yyyy hh:mm
    @Nullable
    private Long cost;

    private String admin_email;
    private String admin_password;
}
