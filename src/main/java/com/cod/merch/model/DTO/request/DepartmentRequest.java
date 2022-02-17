package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class DepartmentRequest {
    @Nullable
    private String name;
    @Nullable
    private String description;

    private String admin_email;
    private String admin_password;
}
