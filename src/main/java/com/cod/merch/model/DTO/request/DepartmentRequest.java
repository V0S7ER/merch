package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class DepartmentRequest {
    @Nullable
    String name;
    @Nullable
    String description;

    String admin_email;
    String admin_password;
}
