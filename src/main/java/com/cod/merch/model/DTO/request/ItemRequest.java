package com.cod.merch.model.DTO.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class ItemRequest {
    @Nullable
    private String name;
    @Nullable
    private Long price;
    @Nullable
    private String description;

    private String admin_email;
    private String admin_password;
}
