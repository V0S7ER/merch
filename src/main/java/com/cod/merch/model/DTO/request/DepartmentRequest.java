package com.cod.merch.model.DTO.request;

import org.springframework.lang.Nullable;

public class DepartmentRequest {
    @Nullable
    String name;
    @Nullable
    String description;

    String admin_email;
    String admin_password;
}
