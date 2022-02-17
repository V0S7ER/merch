package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ChangeUserRequest {
    @Nullable
    private String email;
    @Nullable
    private String name;
    @Nullable
    private String surname;
    @Nullable
    private Boolean sex;
    @Nullable
    private Long department_id;
    @Nullable
    private String birthday; //"DD.MM.YYYY"
    @Nullable
    private String photo;

    private String user_email;
    private String user_password;
}
