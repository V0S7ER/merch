package com.cod.merch.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String password;
    private boolean sex;
    private Long department_id;
    private String email;
}
