package com.cod.merch.model.DTO.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ItemRequest {
    @Nullable
    private String name;
    @Nullable
    private Long price;
    @Nullable
    private String description;
    @Nullable
    private String photo;

    private String admin_email;
    private String admin_password;
}
